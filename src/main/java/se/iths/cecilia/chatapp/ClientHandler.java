package se.iths.cecilia.chatapp;

import se.iths.cecilia.chatapp.model.Message;
import se.iths.cecilia.chatapp.model.User;
import se.iths.cecilia.chatapp.service.MessageService;
import se.iths.cecilia.chatapp.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final ChatServer server;
    private final UserService userService;
    private final MessageService messageService;

    private PrintWriter out;
    private User user;

    public ClientHandler(Socket socket,
                         ChatServer server,
                         UserService userService,
                         MessageService messageService) {
        this.socket = socket;
        this.server = server;
        this.userService = userService;
        this.messageService = messageService;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void run() {
        try (
                BufferedReader inputReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            this.out = writer;

            writer.println("Välkommen! Har du redan ett konto? (ja/nej)");
            String answer = inputReader.readLine();
            boolean existingUser = false;

            if ("ja".equalsIgnoreCase(answer)) {
                writer.println("Ange användarnamn:");
                String username = inputReader.readLine();

                writer.println("Ange lösenord:");
                String password = inputReader.readLine();

                user = userService.login(username, password);
                writer.println("I have returned.");

                if (user == null) {
                    writer.println("Fel användarnamn eller lösenord.");
                    writer.println("Skriv /quit för att avsluta.");
                } else {
                    existingUser = true;
                }

            } else {
                writer.println("Skapa nytt konto. Ange användarnamn:");
                String username = inputReader.readLine();

                writer.println("Ange lösenord:");
                String password = inputReader.readLine();

                user = userService.register(new User(username, password));

                writer.println("Konto skapat. Välkommen, " + user.getUsername() + "!");
            }

            writer.println("Du är inloggad som: " + user.getUsername());

            if (existingUser) {
                List<Message> messageForUser = user.getMessages();
                if (messageForUser != null && !messageForUser.isEmpty()) {
                    writer.println("Din chatthistorik:");
                    for (Message m : messageForUser) {
                        writer.println("[" + m.getTimestamp() + "] " + m.getText());
                    }
                }
            }

            writer.println("Nu kan du börja chatta.");
            writer.println("/mymsgs visar dina meddelanden.");
            writer.println("/quit avslutar sessionen.");

            String msg;
            while ((msg = inputReader.readLine()) != null) {

                if (msg.equalsIgnoreCase("/quit")) {
                    break;

                } else if (msg.equalsIgnoreCase("/mymsgs")) {
                    List<Message> messages = messageService.getMessages(user.getId());
                    if (messages.isEmpty()) {
                        writer.println("Inga meddelanden sparade.");
                    } else {
                        for (Message m : messages) {
                            writer.println("[" + m.getTimestamp() + "] " + m.getText());
                        }
                    }

                } else {
                    server.broadcast(msg, this);
                    messageService.save(new Message(user, msg, LocalDateTime.now()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.removeClient(this);
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    public void sendMessage(String msg) {
        if (out != null) out.println(msg);
    }
}
