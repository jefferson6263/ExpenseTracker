import React from 'react'
import DashBoard from '../Components/VerticalDashBoard'
import AllExpenses from '../Components/AllExpenses'

const ViewExpensePage = (props) => {
    const {bearer} = props

    return (
        <>

            <h1>View Expenses</h1>
            <DashBoard bearer={bearer}/>
            <AllExpenses bearer={bearer}/>
        
        </>
    )
}

export default ViewExpensePage