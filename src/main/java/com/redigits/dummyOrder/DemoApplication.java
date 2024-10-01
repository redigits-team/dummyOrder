package com.redigits.dummyOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Dummy data
    private List<Client> clients = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<ReturnRequest> returnRequests = new ArrayList<>();
    private List<Shipment> shipments = new ArrayList<>();


    public DemoApplication() {
        // Populate some dummy data
        clients.add(new Client(1, "Mario Rossi", "mario.rossi@example.com", "+39 345 1234567", 
            new Address("Via Roma, 10", "Roma", "00100", "Italia")));
        clients.add(new Client(2, "Luca Bianchi", "luca.bianchi@example.com", "+39 340 9876543", 
            new Address("Corso Milano, 20", "Milano", "20100", "Italia")));
        clients.add(new Client(3, "Giulia Verdi", "giulia.verdi@example.com", "+39 339 1112233", 
            new Address("Via Napoli, 30", "Napoli", "80100", "Italia")));
        clients.add(new Client(4, "Domenico Scelza", "domenico.scelza@gmail.com", "+39 340 9876543", 
            new Address("via petrocchi , 13", "Battipaglia", "84091", "Italia")));
        clients.add(new Client(5, "Laura Mandelli", "mandelli.laura@kasanova.it", "+39 339 1112233", 
            new Address("Via Napoli, 30", "Napoli", "80100", "Italia")));
            

        payments.add(new Payment(1, 1, 100.00, "EUR", "Completed", "Credit Card", "2024-09-25"));
        payments.add(new Payment(2, 2, 250.00, "EUR", "Pending", "PayPal", "2024-09-26"));
        payments.add(new Payment(3, 3, 75.50, "EUR", "Completed", "Bank Transfer", "2024-09-24"));

        payments.add(new Payment(4, 41234, 100.00, "EUR", "Completed", "Credit Card", "2024-09-25"));
        payments.add(new Payment(5, 346421, 250.00, "EUR", "Pending", "PayPal", "2024-09-26"));
        payments.add(new Payment(6, 3244421, 75.50, "EUR", "Completed", "Bank Transfer", "2024-09-24"));
        payments.add(new Payment(7, 3244422, 7.50, "EUR", "Completed", "Bank Transfer", "2024-09-24"));


        orders.add(new Order(1, 1, "Padella triplay", 1, 15.50, new Address("Via Roma, 10", "Roma", "00100", "Italia"), 1,"Spedito",6));
        orders.add(new Order(2, 2, "Friggitrice ad aria", 2, 45.90, new Address("Corso Milano, 20", "Milano", "20100", "Italia"), 2, "In lavorazione",7));
        orders.add(new Order(3, 3, "Tavolo hasperide", 1, 175.00, new Address("Via Napoli, 30", "Napoli", "80100", "Italia"), 3, "Spedito",8));

        orders.add(new Order(41234, 5, "Padella triplay", 1, 15.50, new Address("Via Napoli, 30", "Napoli", "80100", "Italia"), 4,"Spedito",1));
        orders.add(new Order(346421, 5, "Friggitrice ad aria", 1, 45.90, new Address("Via Napoli, 30", "Napoli", "80100", "Italia"), 5,"In lavorazione",4));
        orders.add(new Order(3244421, 5, "Tavolo hasperide", 1, 175.00, new Address("Via Napoli, 30", "Napoli", "80100", "Italia"), 6,"consegnato",2));
        orders.add(new Order(3244422, 5, "Filtro acqua brt", 1, 7.50, new Address("Via Napoli, 30", "Napoli", "80100", "Italia"), 7,"Spedito",3));


        shipments.add(new Shipment(1,41234,"Roma", "In consegna", "30-09-24", "10-10-24"));
        shipments.add(new Shipment(2,3244421,"Roma", "Consegnato", "30-09-24", "10-10-24"));
        shipments.add(new Shipment(3,3244422,"Roma", "In transito", "30-09-24", "10-10-24")); 
        shipments.add(new Shipment(4,346421,"Roma", "Non partita", "", "10-10-24"));
        shipments.add(new Shipment(6,1,"Roma", "Non partita", "", "10-10-24"));
        shipments.add(new Shipment(7,2,"Roma", "Non partita", "", "10-10-24"));
        shipments.add(new Shipment(8,3,"Roma", "Non partita", "", "10-10-24"));



    }

    @Operation(summary = "Retrieve a list of all orders", description = "Returns a detailed list of orders with client and payment information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of orders returned successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/orders")
    public List<OrderResponse> getOrders() {
        List<OrderResponse> ordersWithDetails = new ArrayList<>();
        for (Order order : orders) {
            Client client = clients.stream().filter(c -> c.getId() == order.getClientId()).findFirst().orElse(null);
            Payment payment = payments.stream().filter(p -> p.getId() == order.getPaymentId()).findFirst().orElse(null);
            Shipment shipment = shipments.stream().filter(p -> p.getId() == order.getShipmentId()).findFirst().orElse(null);

            ordersWithDetails.add(new OrderResponse(order, client, payment,shipment));
        }
        return ordersWithDetails;
    }

    @Operation(summary = "Retrieve a list of clients", description = "Returns a list of all clients")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of clients returned successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/clients")
    public List<Client> getClients() {
        return clients;
    }

    @Operation(summary = "Retrieve payment details for a specific order", description = "Returns the payment details for the specified order ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment details returned successfully"),
        @ApiResponse(responseCode = "404", description = "Payment not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/payment/{orderId}")
    public Payment getPayment(
        @Parameter(description = "ID of the order to retrieve payment for", required = true)
        @PathVariable int orderId) {
        return payments.stream().filter(p -> p.getOrderId() == orderId).findFirst().orElse(null);
    }

    @GetMapping("/shipment/{orderId}")
    public Shipment getShipment(
        @Parameter(description = "ID of the order to retrieve shipment for", required = true)
        @PathVariable int orderId) {
        return shipments.stream().filter(p -> p.getOrderId() == orderId).findFirst().orElse(null);
    }

    @Operation(summary = "Submit a new return request", description = "Creates a new return request for an order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Return request created successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/return")
    public ReturnRequest createReturnRequest(@RequestBody ReturnRequest newReturnRequest) {
        // Simula l'aggiunta di una nuova richiesta di reso
        newReturnRequest.setId(returnRequests.size() + 1);
        newReturnRequest.setRequestDate(LocalDate.now());
        newReturnRequest.setStatus("Pending");
        returnRequests.add(newReturnRequest);
        return newReturnRequest;
    }

    @Operation(summary = "Retrieve all return requests", description = "Returns a list of all return requests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of return requests returned successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/returns")
    public List<ReturnRequest> getReturnRequests() {
        return returnRequests;
    }
}