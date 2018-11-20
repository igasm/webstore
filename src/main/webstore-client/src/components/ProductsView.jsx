import React from 'react';

function ProductsList(props){
    const products = props.products;
    const productsList = products.map((product) => 
        <li key={product.productId}>{product.name}</li>
    );
    return (<ul>{productsList}</ul>);
}

const ProductsView = ({products}) => {
    return <ProductsList products={products} />
}

export default ProductsView;