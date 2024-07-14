package org.itt.controller;

import org.itt.constant.ChefAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChefControllerClient {

    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    public ChefControllerClient(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public void handleChefTasks() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String menu = (String) objectInputStream.readObject();
                System.out.println(menu);

                int userChoice = Integer.parseInt(bufferedReader.readLine());
                objectOutputStream.writeObject(userChoice);

                ChefAction choice;
                try {
                    choice = ChefAction.fromValue(userChoice);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }

                switch (choice) {
                    case VIEW_HIGH_RATED_ITEMS, VIEW_DISCARD_MENU:
                        handleResponse();
                        break;
                    case SELECT_ITEMS_FOR_NEXT_DAY:
                        handleSelectItemsForNextDay(bufferedReader);
                        handleResponse();
                        break;
                    case REMOVE_FOOD_ITEM:
                        handleRemoveFoodItem(bufferedReader);
                        handleResponse();
                        break;
                    case ADD_ITEM_FOR_DETAILED_FEEDBACK:
                        handleAddItemForDetailedFeedback(bufferedReader);
                        handleResponse();
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

    private void handleResponse() throws IOException, ClassNotFoundException {
        String response = (String) objectInputStream.readObject();
        System.out.println(response);
    }

    private void handleSelectItemsForNextDay(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the IDs of the items to select for the next day (comma-separated): ");
        String input = bufferedReader.readLine();
        objectOutputStream.writeObject(input);
    }

    private void handleRemoveFoodItem(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the id of the food item to remove: ");
        String itemName = bufferedReader.readLine();
        objectOutputStream.writeObject(itemName);
    }

    private void handleAddItemForDetailedFeedback(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the id of the item to mark for detailed feedback: ");
        String itemId = bufferedReader.readLine();
        objectOutputStream.writeObject(itemId);
    }
}
