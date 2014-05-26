/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import com.ptsesd.groepb.shared.Item;
import com.ptsesd.groepb.shared.User;
import com.ptsesd.groepb.shared.UserItem;
import com.ptsesd.groepb.shared.jms.InsertUserMessage;
import dao.ItemDAO;
import dao.ItemDAO_JPAImpl;
import dao.UserDAO;
import dao.UserDAO_Impl;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import jms.RegisterUserGateway;

/**
 *
 * @author Administrator
 */
@WebService
public class StoreWebservice {

    UserDAO userDAO;
    ItemDAO itemDAO;
    RegisterUserGateway rug;

    public StoreWebservice(EntityManager em) {
        userDAO = new UserDAO_Impl(em);
        itemDAO = new ItemDAO_JPAImpl(em);
        this.rug = new RegisterUserGateway();
    }

    public boolean registerUser(String username, String password) {
        System.out.println(String.format("---[STOREWEBSERVICE] Got register user request User [%s] Password [%s]", new Object[]{username, password}));
        ArrayList<UserItem> inventory = new ArrayList<>();

        User user = new User(inventory, username, password);
        boolean registerUser = userDAO.registerUser(user);
        if (registerUser) {
            InsertUserMessage ium = new InsertUserMessage(user.getUserId(), user.getUsername(), user.getPassword());
            this.rug.sendRegisterRequest(ium);
        }
        System.out.println("---[STOREWEBSERVICE] Registerd user");
        return registerUser;
    }

    public boolean login(String username, String password) {
        System.out.println(String.format("---[STOREWEBSERVICE] Login request user [%s]", username));
        return userDAO.login(username, password);
    }

    public boolean buyItem(String username, long itemId) {
        System.out.println(String.format("---[STOREWEBSERVICE] BuyItem request User: [%s] Item: [%s]", username, itemId));
        User user = userDAO.find(username);
        Item item = itemDAO.find(itemId);
        return itemDAO.buyItem(user, item);
    }

    public List<Item> getAllItems() {
        System.out.println("---[STOREWEBSERVICE] GetAllItems request");
        List<Item> findAll = itemDAO.findAll();
        return findAll;
    }

    public List<Item> getItemsForUser(String username) {
        User find = userDAO.find(username);
        List<UserItem> findAllUserItem = itemDAO.findAllUserItem(find);
        List<Item> userItems = new ArrayList<>();
        for (UserItem userItem : findAllUserItem) {
            userItems.add(userItem.getItem());
        }
        return userItems;
    }

    public float[] getResourcesForUser(String username) {
        User find = userDAO.find(username);
        float[] resources = new float[3];
        resources[0] = find.getResource_normal();
        resources[1] = find.getResource_magic();
        resources[2] = find.getResource_rare();
        return resources;
    }

    public boolean addResources(String username, float value, String resourceType) {
        System.out.println(String.format("---[STOREWEBSERVICE] AddResources request User [%s] || Value [%s] || ResourceType [%s]", new Object[]{username, value, resourceType}));
        User find = userDAO.find(username);
        if (null != resourceType) {
            switch (resourceType) {
                case "normal":
                    int resource_normal = find.getResource_normal();

                    resource_normal += value;
                    find.setResource_normal(resource_normal);
                    userDAO.edit(find);
                    return true;

                case "magic":
                    int resource_magic = find.getResource_magic();

                    resource_magic += value;
                    find.setResource_magic(resource_magic);
                    userDAO.edit(find);
                    return true;

                case "rare":
                    int resource_rare = find.getResource_rare();

                    resource_rare += value;
                    find.setResource_rare(resource_rare);
                    userDAO.edit(find);
                    return true;

                default:
                    return false;
            }
        }
        return false;
    }
}
