import {Order} from '../../entity/order';

export interface IOrderService {
  getAllOrderToPromise();

  saveOrder(order: Order);


}
