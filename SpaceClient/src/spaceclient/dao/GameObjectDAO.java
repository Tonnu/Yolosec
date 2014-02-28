/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceclient.dao;

import java.util.List;
import spaceclient.game.GameObject;
import spaceclient.game.Spaceship;

/**
 *
 * @author Toon
 */
public interface GameObjectDAO {
    List<GameObject> getGameObjects();
    void setGameObjects(List<GameObject> objects);
    List<Spaceship> getSpaceships();
}
