import React from 'react';
import data from '../data/data.json';

export default class OrderAnalyticsView extends React.Component {
  render() {
    const orders = data;
    console.log(orders);
    return (
        <div>
          <div>{orders.map((order) =>
              <div key={order.order_counts_under_threshold.order_count_under_threshold}>
                {order.order_counts_under_threshold.order_count_under_threshold}
              </div>)}
          </div>
        </div>
    );
  }
}
