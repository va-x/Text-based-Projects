import java.util.Scanner;

public class HauntedHouse {

		static class Node {
				String name; 
				Node north, south, east, west; 

				public Node(String name) {
						this.name = name;
				}

				public String getConnections() {
						StringBuilder connections = new StringBuilder();
						if (north != null) connections.append("north ");
						if (south != null) connections.append("south ");
						if (east != null) connections.append("east ");
						if (west != null) connections.append("west ");
						return connections.toString().trim();
				}
		}

		public static void start(Scanner scnr) {
				System.out.println("\nEscape the Haunted House.\n");

				Node A = new Node("A");
				Node B = new Node("B");
				Node C = new Node("C");
				Node D = new Node("D");
				Node E = new Node("E");
				Node F = new Node("F");
				Node G = new Node("G");
				Node H = new Node("H");
				Node I = new Node("I");
				Node J = new Node("J");
				Node K = new Node("K");
				Node L = new Node("L"); 

				A.east = B; A.south = F;
				B.west = A; B.east = C; B.south = F;
				C.west = B; C.east = D;
				D.west = C; D.south = H;
				E.east = F;
				F.north = A; F.east = L;
				G.north = B; G.east = H; G.south = J; G.west = F;
				H.north = D; H.west = G; H.south = K;
				I.north = F; I.east = J;
				J.north = G; J.west = I; J.east = K;
				K.north = H; K.west = J; K.east = L;
				L.west = K;

				Node currentRoom = A;

				while (!currentRoom.name.equals("L")) {
						System.out.println("You are in room " + currentRoom.name + " of the Haunted House. You can go " + currentRoom.getConnections() + ".");
						System.out.print("Enter direction (N/S/E/W): ");

						String input = scnr.nextLine().trim().toUpperCase();
						if (input.length() == 0) {
								System.out.println("Invalid input! Please enter a direction.");
								continue;
						}

						char direction = input.charAt(0);
						Node nextRoom = null;

						switch (direction) {
								case 'N': nextRoom = currentRoom.north; break;
								case 'S': nextRoom = currentRoom.south; break;
								case 'E': nextRoom = currentRoom.east; break;
								case 'W': nextRoom = currentRoom.west; break;
								default:
										System.out.println("Invalid direction! Try again.");
										continue;
						}

						if (nextRoom != null) {
								currentRoom = nextRoom;
						} else {
								System.out.println("You can't go in that direction!");
						}
				}

				System.out.println("You are in room L of the Haunted House. You made it out alive!");
		}

		public static void main(String[] args) {
				Scanner scnr = new Scanner(System.in);
				start(scnr);
				scnr.close();
		}
}


