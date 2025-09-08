package co.unicauca.microkernel.plugins.colombia;
import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.entities.Product;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;

public class ColombiaDeliveryPlugin implements IDeliveryPlugin {

    @Override
    public double calculateCost(Delivery delivery) {

        Product product = delivery.getProduct();
        double weight = product.getWeight();
        double distance = delivery.getDistance();

        double cost;

        if (weight <= 2) {

            cost = (distance <= 20) ? 3 : 4;

        } else {

            cost = (distance <= 20) ? 4 : 5;

        }

        return cost;

    }
}
