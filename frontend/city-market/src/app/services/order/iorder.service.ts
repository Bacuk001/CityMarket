import {Order} from '../../entities/order';

/**
 * Interface describing methods for working with product orders.
 */
export interface IOrderService {
  /**
   * The method generates a request to receive all orders.
   */
  getAllOrderToPromise();

  /**
   * The method generates a request to save the order. The method takes the order, the maize in
   * which the order and the product id are made which is in order.
   */
  saveOrder(order: Order, productId: number, marketId: number);


}
