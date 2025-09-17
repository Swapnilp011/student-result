import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ViewResult = ({ match }) => {
    const [marks, setMarks] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/students/${match.params.id}/marks`)
            .then(res => setMarks(res.data))
            .catch(err => console.log(err));
    }, [match.params.id]);

    return (
        <div>
            <h2>View Result</h2>
            {marks ? (
                <table>
                    <tbody>
                        <tr>
                            <td>Subject 1</td>
                            <td>{marks.subject1}</td>
                        </tr>
                        <tr>
                            <td>Subject 2</td>
                            <td>{marks.subject2}</td>
                        </tr>
                        <tr>
                            <td>Subject 3</td>
                            <td>{marks.subject3}</td>
                        </tr>
                        <tr>
                            <td>Total</td>
                            <td>{marks.total}</td>
                        </tr>
                        <tr>
                            <td>Percentage</td>
                            <td>{marks.percentage}%</td>
                        </tr>
                    </tbody>
                </table>
            ) : (
                <p>No result found</p>
            )}
        </div>
    );
};

export default ViewResult;