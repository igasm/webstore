import React, { Component } from 'react';
import './App.css';
import { products, customers } from './_testData_/data';
import ProductsView from './components/ProductsView';
import CustomersView from './components/CustomersView';

class App extends Component {
  render() {
    return (
      <>
        <ProductsView products={products} />
        <CustomersView customers={customers} />
      </>
    );
  }
}

export default App;
