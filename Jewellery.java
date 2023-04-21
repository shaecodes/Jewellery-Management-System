package src;
import java.util.ArrayList;

public class Jewellery {
    private String name;
    private String brand;
    private String type;
    private double price;
    private String sku;
    private String origin;
    private String material;
    private boolean available;
    private int weight;

    /**
     * 
     * @param sku
     * @param name
     * @param brand
     * @param type
     * @param origin
     * @param material
     * @param weight
     * @param price
     * @param available
     */
    public Jewellery(String sku, String name, String brand, String type, String origin, String material, int weight,
            double price, boolean available) {
        this.sku = sku;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.weight = weight;
        this.price = price;
        this.origin = origin;
        this.material = material;
        this.available = available;
    }


    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getSKU() {
        return sku;
    }

    public String getOrigin() {
        return origin;
    }

    public String getMaterial() {
        return material;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getWeight() {
        return weight;
    }

    /**
     * 
     * @param pricePerGram
     * @return
     */

    public double calculatePriceByWeight(double pricePerGram) {
        return weight * pricePerGram;
    }

    /**
     * 
     * @param jewelleryList
     * @return
     */
    public double calculateTotalPrice(ArrayList<Jewellery> jewelleryList) {
        double totalPrice = 0;
        for (Jewellery jewelry : jewelleryList) {
            totalPrice += jewelry.getPrice();
        }
        return totalPrice;
    }

    /**
     * 
     * @param available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
