# chatting_Application
Chatting Application with the help of Java Swing, AWT and Socket programming


This Java-based chatting application enables real-time communication between two users using Java Swing for the graphical interface, AWT for additional components, and Socket programming for data transmission. The interface is built with Swing and AWT, where each user can send and receive messages in a chat window, mimicking popular chat application layouts. A `ServerSocket` on the server side listens for incoming connections, and `Socket` on the client side handles outgoing messages, both communicating over data streams (`DataInputStream` and `DataOutputStream`). Each message is displayed in dynamically created panels, timestamped and aligned based on sender or receiver. This simple yet efficient structure demonstrates basic networking principles in Java, allowing direct, text-based communication between two devices connected over a network.
