/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package co.unicauca.microkernel;
        //co.unicauca.microkernel.plugins.argentina

import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.entities.Product;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;

/**
 *
 * @author crist
 */
public class ArgentinaDeliveryPlugin implements IDeliveryPlugin {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    @Override
    public double calculateCost(Delivery delivery) {
        
        Product product = delivery.getProduct();
        double weight = product.getWeight();
        double distance = delivery.getDistance();

        double cost;

        if (weight <= 2) {

            cost = (distance <= 50) ? 4 : 8;

        } else {

            cost = (distance <= 60) ? 7 : 8;

        }

        return cost;
    }
}
