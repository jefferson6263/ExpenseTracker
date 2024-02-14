
import React from 'react'
import DashBoard from '../Components/VerticalDashBoard'

const StatisticsPage = (props) => {
    const {bearer} = props
    return (
        <>

            <h1>statistics</h1>
            <DashBoard bearer={bearer}/>
        
        </>
    )
}

export default StatisticsPage