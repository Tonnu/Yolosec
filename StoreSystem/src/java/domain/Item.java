/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Collection;

public class Item {

    private long id;
    private String name;
    private String description;
    private Collection<Resource> resources;
    private Collection<Stat> stats;
    private boolean equipped;
    private String image;

    public Item(long id, String name, String description, Collection<Resource> resources, Collection<Stat> stats, boolean equipped, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.resources = resources;
        this.stats = stats;
        this.equipped = equipped;
        this.image = image;
    }

    /**
     * Get the id of the item
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id of the item
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the name of the item
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the item
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description of the item
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the item
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the resources of the item
     *
     * @return
     */
    public Collection<Resource> getResources() {
        return resources;
    }

    /**
     * Set the resources of the item
     *
     * @param resources
     */
    public void setResources(Collection<Resource> resources) {
        this.resources = resources;
    }

    /**
     * Get the stats of the item
     *
     * @return
     */
    public Collection<Stat> getStats() {
        return stats;
    }

    /**
     * Set the stats of the item
     *
     * @param stats
     */
    public void setStats(Collection<Stat> stats) {
        this.stats = stats;
    }

    /**
     * Get if the item is equipped
     *
     * @return
     */
    public boolean isEquipped() {
        return equipped;
    }

    /**
     * Set if the item is equipped
     *
     * @param equipped
     */
    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    /**
     * Get the path of the image
     *
     * @return
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the path of the image
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
