import React from 'react';

export default class OrderMessage extends React.Component {
  render() {
    const order = this.props.order;
    const orderCountGreaterThanTen = <span style={{color: 'red'}}>
      {order.order_counts_above_threshold.order_count_above_threshold}
    </span>;

    return (
        <tr>
          <td>{order}</td>
          <td>{orderCountGreaterThanTen}</td>
          <td>{order.order_counts_above_threshold.order_count_above_threshold}</td>
        </tr>
    );
  }
}
