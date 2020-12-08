import React from 'react';
import './App.css';
import OrderMessage from './OrderMessage';

function App() {
  ReactDOM.render(
      <OrderMessage counts={ORDERCOUNTS}/>,
      document.getElementById('container')
  );
}

const ORDERCOUNTS = [
  {
    "order_counts_under_threshold": {
      "threshold": 10,
      "order_count_under_threshold": 105357
    },
    "order_counts_above_threshold": {
      "threshold": 10,
      "order_count_above_threshold": 9904694
    }
  }];
export default App;
