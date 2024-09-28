package com.redigits.dummyOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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

    public DemoApplication() {
        // Populate some dummy data
        clients.add(new Client(1, "Mario Rossi", "mario.rossi@example.com", "+39 345 1234567", 
            new Address("Via Roma, 10", "Roma", "00100", "Italia")));
        clients.add(new Client(2, "Luca Bianchi", "luca.bianchi@example.com", "+39 340 9876543", 
            new Address("Corso Milano, 20", "Milano", "20100", "Italia")));
        clients.add(new Client(3, "Giulia Verdi", "giulia.verdi@example.com", "+39 339 1112233", 
            new Address("Via Napoli, 30", "Napoli", "80100", "Italia")));

        payments.add(new Payment(1, 1, 100.00, "EUR", "Completed", "Credit Card", "2024-09-25"));
        payments.add(new Payment(2, 2, 250.00, "EUR", "Pending", "PayPal", "2024-09-26"));
        payments.add(new Payment(3, 3, 75.50, "EUR", "Completed", "Bank Transfer", "2024-09-24"));

        orders.add(new Order(1, 1, "Laptop", 1, 100.00, new Address("Via Roma, 10", "Roma", "00100", "Italia"), 1));
        orders.add(new Order(2, 2, "Smartphone", 2, 125.00, new Address("Corso Milano, 20", "Milano", "20100", "Italia"), 2));
        orders.add(new Order(3, 3, "Headphones", 1, 75.50, new Address("Via Napoli, 30", "Napoli", "80100", "Italia"), 3));
    }

    // 1. API per la lista degli ordini
    @GetMapping("/orders")
    public List<OrderResponse> getOrders() {
        List<OrderResponse> ordersWithDetails = new ArrayList<>();
        for (Order order : orders) {
            Client client = clients.stream().filter(c -> c.getId() == order.getClientId()).findFirst().orElse(null);
            Payment payment = payments.stream().filter(p -> p.getId() == order.getPaymentId()).findFirst().orElse(null);
            ordersWithDetails.add(new OrderResponse(order, client, payment));
        }
        return ordersWithDetails;
    }

    // 2. API per la lista dei clienti
    @GetMapping("/clients")
    public List<Client> getClients() {
        return clients;
    }

    // 3. API per i dettagli del pagamento di un ordine
    @GetMapping("/payment/{orderId}")
    public Payment getPayment(@PathVariable int orderId) {
        return payments.stream().filter(p -> p.getOrderId() == orderId).findFirst().orElse(null);
    }

    // 4. API per inviare una richiesta di reso
    @PostMapping("/return")
    public ReturnRequest createReturnRequest(@RequestBody ReturnRequest newReturnRequest) {
        // Simula l'aggiunta di una nuova richiesta di reso
        newReturnRequest.setId(returnRequests.size() + 1);
        newReturnRequest.setRequestDate(LocalDate.now());
        newReturnRequest.setStatus("Pending");
        returnRequests.add(newReturnRequest);
        return newReturnRequest;
    }

    // 5. API per ottenere tutte le richieste di reso
    @GetMapping("/returns")
    public List<ReturnRequest> getReturnRequests() {
        return returnRequests;
    }
}
