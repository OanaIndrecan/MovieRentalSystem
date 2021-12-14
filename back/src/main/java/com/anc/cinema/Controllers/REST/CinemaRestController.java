package com.anc.cinema.Controllers.REST;

import com.anc.cinema.Entities.*;
import com.anc.cinema.ICinemaInit.ICinemaService;
import com.anc.cinema.Repositories.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CinemaRestController {
    final static String UPLOADED_FOLDER = System.getProperty("user.home") + "/Pictures/CinemaApp/images/";

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    VilleRepository villeRepository;
    @Autowired
    ICinemaService cinemaService;
    @Autowired
    ProjectionRepository projectionRepository;
    @Autowired
    SeanceRepository seanceRepository;
    @Autowired
    SalleRepository salleRepository;

    @GetMapping(path = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
        Film film = filmRepository.getOne(id);
        File file = new File(UPLOADED_FOLDER + film.getPhoto());
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @GetMapping(path = "/image-by-name/{name:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] imageByPath(@PathVariable(name = "name") String name) {
        File file = new File(UPLOADED_FOLDER + name);
        Path path = Paths.get(file.toURI());
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Didn't found an image with that name!"
            );
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
        if (uploadfile.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "please select a file!"
            );
        }
        try {
            String slug = (new Date().getTime() / 1000) + uploadfile.getOriginalFilename();
            slug = slug.replace(" ", "_");
            byte[] bytes = uploadfile.getBytes();
            File dir = new File(UPLOADED_FOLDER);
            if (!dir.exists()) dir.mkdirs();
            Path path = Paths.get(UPLOADED_FOLDER + slug);
            Files.write(path, bytes);
            return new ResponseEntity(slug, new HttpHeaders(), HttpStatus.OK);

        } catch (IOException e) {
            System.out.println(e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Problems saving the image"
            );
        }
    }


    @PostMapping(path = "/buyTickets")
    public List<Ticket> buyTickets(@RequestBody TicketsForm ticketsForm) {
        List<Ticket> ticketList = new ArrayList<>();
        ticketsForm.getTickets().forEach(ticketId -> {
            System.out.println(ticketId);
            Ticket ticket = ticketRepository.findById(ticketId).get();
            ticket.setNomClient(ticketsForm.getNomClient());
            ticket.setReserve(true);
            ticket.setCodePayment(ticketsForm.getCodePayment());
            ticketRepository.save(ticket);
            ticketList.add(ticket);
        });
        return ticketList;
    }

    @PostMapping(path = "/addFilm")
    public ResponseEntity<Film> addFilm(@RequestPart("filmData") Film filmData,
                                        @RequestPart("file") MultipartFile file) {
        String path = this.uploadFile(file).getBody().toString();
        filmData.setPhoto(path);
        Film film = filmRepository.save(filmData);
        if (film != null) {
            return new ResponseEntity(film, HttpStatus.OK);
        } else throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "Problems saving the film"
        );
    }


    @PostMapping(path = "/modifyMovie")
    public ResponseEntity<Film> modifyFilm(@RequestPart("filmData") Film filmData,
                                           @RequestPart(value = "file", required = false) MultipartFile file) {

        if (!filmRepository.findById(filmData.getId()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Can't find this film");
        }
        if (file != null) {
            filmData.setPhoto(this.uploadFile(file).getBody().toString());
        }
        return new ResponseEntity<>(filmRepository.save(filmData), HttpStatus.OK);

    }

    @PostMapping(path = "/addCinema")
    public ResponseEntity<Boolean> addCinema(@RequestBody CinemaForm cinemaForm) {
        Ville ville = villeRepository.findById(cinemaForm.getCity()).orElse(null);
        if (ville == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Didn't found this city"
            );
        }
        Cinema cinema = new Cinema(null, cinemaForm.getName(),
                cinemaForm.getLongitude(), cinemaForm.getLatitude(),
                cinemaForm.getAltitude(), cinemaForm.getNbrRooms(),
                null, ville);
        cinema = cinemaRepository.save(cinema);

        cinemaService.randomInitCinemaRooms(cinema, cinemaForm.getInit());
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @PostMapping(path = "/updateProjections")
    public ResponseEntity<Boolean> updateProjections(@RequestBody ProjectionsForm projectionsForm) throws ParseException {
        Film film = filmRepository.findById(projectionsForm.getMovieID()).orElse(null);
        Salle salle = salleRepository.findById(projectionsForm.getRoomID()).orElse(null);
        if (film == null || salle == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Didn't found this " + (film == null ? "Film" : "Room")
            );
        }
        projectionRepository.deleteAll(salleRepository.getOne(salle.getId()).getProjections());

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        for (ProjectionItem projectionItem : projectionsForm.getProjections()) {
            Projection projection = new Projection();
            Seance seance = new Seance();
            seance.setHeureDebut(dateFormat.parse(projectionItem.getDate()));
            seance = seanceRepository.save(seance);
            projection.setSeance(seance);
            projection.setDateProjection(new Date());
            projection.setFilm(film);
            projection.setPrix(projectionItem.getPrice());
            projection.setSalle(salle);
            projection.setSeance(seance);
            projection = projectionRepository.save(projection);cinemaService.initTicket(projection);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

class TicketsForm {
    private String nomClient;
    private Integer codePayment;
    private List<Long> tickets = new ArrayList<>();

    public TicketsForm() {
    }

    public String getNomClient() {
        return this.nomClient;
    }

    public Integer getCodePayment() {
        return this.codePayment;
    }

    public List<Long> getTickets() {
        return this.tickets;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setCodePayment(Integer codePayment) {
        this.codePayment = codePayment;
    }

    public void setTickets(List<Long> tickets) {
        this.tickets = tickets;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TicketsForm)) return false;
        final TicketsForm other = (TicketsForm) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$nomClient = this.getNomClient();
        final Object other$nomClient = other.getNomClient();
        if (this$nomClient == null ? other$nomClient != null : !this$nomClient.equals(other$nomClient)) return false;
        final Object this$codePayment = this.getCodePayment();
        final Object other$codePayment = other.getCodePayment();
        if (this$codePayment == null ? other$codePayment != null : !this$codePayment.equals(other$codePayment))
            return false;
        final Object this$tickets = this.getTickets();
        final Object other$tickets = other.getTickets();
        if (this$tickets == null ? other$tickets != null : !this$tickets.equals(other$tickets)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TicketsForm;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nomClient = this.getNomClient();
        result = result * PRIME + ($nomClient == null ? 43 : $nomClient.hashCode());
        final Object $codePayment = this.getCodePayment();
        result = result * PRIME + ($codePayment == null ? 43 : $codePayment.hashCode());
        final Object $tickets = this.getTickets();
        result = result * PRIME + ($tickets == null ? 43 : $tickets.hashCode());
        return result;
    }

    public String toString() {
        return "TicketsForm(nomClient=" + this.getNomClient() + ", codePayment=" + this.getCodePayment() + ", tickets=" + this.getTickets() + ")";
    }
}

class CinemaForm {
    private Boolean init;
    private String name;
    private double longitude, latitude, altitude;
    private int nbrRooms;
    private Long city;

    public CinemaForm() {
    }


    public Boolean getInit() {
        return this.init;
    }

    public String getName() {
        return this.name;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public int getNbrRooms() {
        return this.nbrRooms;
    }

    public Long getCity() {
        return this.city;
    }

    public void setInit(Boolean init) {
        this.init = init;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setNbrRooms(int nbrRooms) {
        this.nbrRooms = nbrRooms;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CinemaForm)) return false;
        final CinemaForm other = (CinemaForm) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$init = this.getInit();
        final Object other$init = other.getInit();
        if (this$init == null ? other$init != null : !this$init.equals(other$init)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (Double.compare(this.getLongitude(), other.getLongitude()) != 0) return false;
        if (Double.compare(this.getLatitude(), other.getLatitude()) != 0) return false;
        if (Double.compare(this.getAltitude(), other.getAltitude()) != 0) return false;
        if (this.getNbrRooms() != other.getNbrRooms()) return false;
        final Object this$city = this.getCity();
        final Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CinemaForm;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $init = this.getInit();
        result = result * PRIME + ($init == null ? 43 : $init.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final long $longitude = Double.doubleToLongBits(this.getLongitude());
        result = result * PRIME + (int) ($longitude >>> 32 ^ $longitude);
        final long $latitude = Double.doubleToLongBits(this.getLatitude());
        result = result * PRIME + (int) ($latitude >>> 32 ^ $latitude);
        final long $altitude = Double.doubleToLongBits(this.getAltitude());
        result = result * PRIME + (int) ($altitude >>> 32 ^ $altitude);
        result = result * PRIME + this.getNbrRooms();
        final Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());
        return result;
    }

    public String toString() {
        return "CinemaForm(init=" + this.getInit() + ", name=" + this.getName() + ", longitude=" + this.getLongitude() + ", latitude=" + this.getLatitude() + ", altitude=" + this.getAltitude() + ", nbrRooms=" + this.getNbrRooms() + ", city=" + this.getCity() + ")";
    }
}

@ToString
class ProjectionsForm {
    private long movieID;
    private long roomID;
    private ProjectionItem[] projections;

    @JsonProperty("movieId")
    public long getMovieID() {
        return movieID;
    }

    @JsonProperty("movieId")
    public void setMovieID(long value) {
        this.movieID = value;
    }

    @JsonProperty("roomId")
    public long getRoomID() {
        return roomID;
    }

    @JsonProperty("roomId")
    public void setRoomID(long value) {
        this.roomID = value;
    }

    @JsonProperty("projections")
    public ProjectionItem[] getProjections() {
        return projections;
    }

    @JsonProperty("projections")
    public void setProjections(ProjectionItem[] value) {
        this.projections = value;
    }
}

@ToString
class ProjectionItem {
    private String date;
    private long price;

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String value) {
        this.date = value;
    }

    @JsonProperty("price")
    public long getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(long value) {
        this.price = value;
    }
}
