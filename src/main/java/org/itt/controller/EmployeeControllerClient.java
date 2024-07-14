package org.itt.controller;

import org.itt.constant.EmployeeAction;
import org.itt.entity.Item;

import java.io.*;
import java.util.List;

public class EmployeeControllerClient {

    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    public EmployeeControllerClient(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public void handleEmployeeTasks(int userId) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String menu = (String) objectInputStream.readObject();
                System.out.println(menu);

                int userChoice = Integer.parseInt(bufferedReader.readLine().trim());
                objectOutputStream.writeObject(userChoice);
                objectOutputStream.writeObject(userId);

                EmployeeAction choice;
                try {
                    choice = EmployeeAction.fromValue(userChoice);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }

                switch (choice) {
                    case ORDER_FOOD:
                        handleOrderFood(bufferedReader);
                        break;
                    case VIEW_ORDER_HISTORY:
                    case VIEW_NOTIFICATIONS:
                    case GET_RECOMMENDATIONS:
                        handleResponse();
                        break;
                    case POLL_FOR_NEXT_DAY_ITEMS:
                        handlePollForNextDayItems(bufferedReader);
                        break;
                    case GIVE_FEEDBACK:
                        handleGiveFeedback(bufferedReader);
                        break;
                    case GIVE_DETAILED_FEEDBACK:
                        handleGiveDetailedFeedback(bufferedReader);
                        break;
                    case UPDATE_PROFILE:
                        handleUpdateProfile(bufferedReader);
                        break;
                    case GET_RECOMMENDATION_BY_PROFILE:
                        handleGetRecommendations(userId);
                        break;
                    case EXIT:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while processing your request. Please try again.");
        }
    }

    private void handleOrderFood(BufferedReader bufferedReader) throws IOException, ClassNotFoundException {
        handleResponse();

        System.out.print("Enter the item ID to order: ");
        int itemId = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(itemId);

        handleResponse();
    }

    private void handlePollForNextDayItems(BufferedReader bufferedReader) throws IOException, ClassNotFoundException {
        handleResponse();

        System.out.print("Enter the item ID to poll for: ");
        int itemId = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(itemId);

        handleResponse();
    }

    private void handleGiveFeedback(BufferedReader bufferedReader) throws IOException, ClassNotFoundException {
        System.out.print("Enter the order ID: ");
        int orderId = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(orderId);

        System.out.print("Enter the item ID: ");
        int itemId = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(itemId);

        System.out.print("Enter the rating (1-5): ");
        int rating = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(rating);

        System.out.print("Enter your comments: ");
        String comment = bufferedReader.readLine();
        objectOutputStream.writeObject(comment);

        handleResponse();
    }

    private void handleGiveDetailedFeedback(BufferedReader bufferedReader) throws IOException, ClassNotFoundException {
        objectOutputStream.writeObject("FETCH_DETAILED_FEEDBACK_ITEMS");
        handleResponse();

        System.out.print("Enter the item ID: ");
        int itemId = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(itemId);

        System.out.print(objectInputStream.readObject() + " ");
        String answer1 = bufferedReader.readLine();
        objectOutputStream.writeObject(answer1);

        System.out.print(objectInputStream.readObject() + " ");
        String answer2 = bufferedReader.readLine();
        objectOutputStream.writeObject(answer2);

        System.out.print(objectInputStream.readObject() + " ");
        String answer3 = bufferedReader.readLine();
        objectOutputStream.writeObject(answer3);

        handleResponse();
    }

    private void handleUpdateProfile(BufferedReader bufferedReader) throws IOException, ClassNotFoundException {
        System.out.print("Enter your food type choice: ");
        int foodTypeChoice = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(foodTypeChoice);

        System.out.print("Enter your spice level choice: ");
        int spiceLevelChoice = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(spiceLevelChoice);

        System.out.print("Enter your cuisine choice: ");
        int cuisineChoice = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(cuisineChoice);

        System.out.print("Enter your sweet tooth choice: ");
        int sweetToothChoice = Integer.parseInt(bufferedReader.readLine().trim());
        objectOutputStream.writeObject(sweetToothChoice);

        handleResponse();
    }

    private void handleGetRecommendations(int userId) throws IOException, ClassNotFoundException {
        objectOutputStream.writeObject(EmployeeAction.GET_RECOMMENDATION_BY_PROFILE.ordinal());
        objectOutputStream.writeObject(userId);

        Object response = objectInputStream.readObject();
        if (response instanceof List) {
            List<Item> recommendations = (List<Item>) response;
            System.out.println("Recommended Items:");
            for (Item item : recommendations) {
                System.out.println(item);
            }
        } else {
            System.out.println("Failed to get recommendations: " + response);
        }}
    private void handleResponse() throws IOException, ClassNotFoundException {
        String response = (String) objectInputStream.readObject();
        System.out.println(response);
    }
}
