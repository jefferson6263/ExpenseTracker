

import React from 'react'
import DashBoard from '../Components/VerticalDashBoard'

const UpdateDetailsPage = (props) => {
    const {bearer} = props
    return (
        <>

            <h1>update Details</h1>
            <DashBoard bearer={bearer}/>
        
        </>
    )
}

export default UpdateDetailsPage