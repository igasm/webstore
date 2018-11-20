import React from 'react';

function CustomersList(props){
    const customers = props.customers;
    const customersList = customers.map((customer) => {
        return <li key={customer.customerId}>{customer.name}</li>
    });
    return (<ul>{customersList}</ul>);
}

const CustomersView = ({customers}) => {
    return <CustomersList customers={customers} />
}

export default CustomersView;