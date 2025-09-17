import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Topper = () => {
    const [topper, setTopper] = useState(null);

    useEffect(() => {
        axios.get('http://localhost:8080/api/students/topper')
            .then(res => setTopper(res.data))
            .catch(err => console.log(err));
    }, []);

    return (
        <div>
            <h2>Topper</h2>
            {topper ? (
                <div>
                    <h3>{topper.student.name}</h3>
                    <p>Percentage: {topper.marks.percentage}%</p>
                </div>
            ) : (
                <p>No topper found</p>
            )}
        </div>
    );
};

export default Topper;