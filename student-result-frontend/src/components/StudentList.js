import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const StudentList = () => {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/students')
            .then(res => setStudents(res.data))
            .catch(err => console.log(err));
    }, []);

    return (
        <div>
            <h2>Students</h2>
            <Link to="/add-student">Add Student</Link>
            <Link to="/topper" style={{ marginLeft: '10px' }}>View Topper</Link>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>DOB</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {students.map(student => (
                        <tr key={student.id}>
                            <td>{student.name}</td>
                            <td>{student.dob}</td>
                            <td>
                                <Link to={`/add-marks/${student.id}`}>Add Marks</Link>
                                <Link to={`/view-result/${student.id}`} style={{ marginLeft: '10px' }}>View Result</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default StudentList;