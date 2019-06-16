package com.company;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

import static com.company.Helper.getType;
import static com.company.Message.searchForAMessageToSolve;
import static com.company.ShopItem.shopForItems;

public class Main {

    private static final String baseURL = "https://dragonsofmugloar.com/";
    private static ArrayList<Message> messages = new ArrayList<>();
    private static Gson g = new Gson();

    public static void main(String[] args) throws Exception {

        // initialize the game
        Game gameObject = startGame();

        try {
            while (true){
                // null the messages list in the beginning of the turn
                messages = null;

                parseMessages(gameObject.gameId);
                Message messageToSolve =searchForAMessageToSolve(messages);

                Response response = g.fromJson(solveMessage(messageToSolve.adId, gameObject.gameId), Response.class);
                if (response.lives == 1){
                    Connection.request(baseURL + "api/v2/"+ gameObject.gameId +"/shop/buy/hpot", "POST");
                }
                if (response.gold < 200){
                    ArrayList shopItemList = parseShopItems(gameObject.gameId);
                    String itemId = shopForItems(shopItemList, response.gold);

                    if(!itemId.equals("")){
                        // if item is found send request to buy that item
                        Connection.request(baseURL + "api/v2/"+ gameObject.gameId +"/shop/buy/" + itemId, "POST");
                    }

                }
                System.out.println(messageToSolve.message);
                System.out.println("Score = " + response.score + " Turn = " + response.turn + " Lives = " + response.lives);
            }
        } catch (Exception exception){
            System.out.println( "   ______                                            \n" +
                                "  / ____/___ _____ ___  ___     ____ _   _____  _____\n" +
                                " / / __/ __ `/ __ `__ \\/ _ \\   / __ \\ | / / _ \\/ ___/\n" +
                                "/ /_/ / /_/ / / / / / /  __/  / /_/ / |/ /  __/ /    \n" +
                                "\\____/\\__,_/_/ /_/ /_/\\___/   \\____/|___/\\___/_/     ");
        }

    }

    public static void parseMessages(String gameId) throws Exception {
        String messagesString = Connection.request(baseURL + "api/v2/" + gameId + "/messages", "GET");
        messages = g.fromJson(messagesString, getType(List.class, Message.class));
    }

    public static String solveMessage(String adId, String gameId) throws Exception {
        return Connection.request(baseURL + "api/v2/" + gameId + "/solve/" + adId,"POST");
    }
    public static String getShopItems(String gameId) throws Exception {
        return Connection.request(baseURL + "api/v2/"+ gameId + "/shop", "GET");
    }

    public static ArrayList parseShopItems(String gameId) throws Exception {
        String ShopItems = getShopItems(gameId);
        return g.fromJson(ShopItems, getType(List.class, ShopItem.class));
    }

    public static Game startGame() throws Exception {
        return g.fromJson(Connection.request(baseURL + "api/v2/game/start", "POST"), Game.class);
    }
}