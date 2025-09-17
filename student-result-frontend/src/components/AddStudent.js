import React, { useState } from 'react';
import axios from 'axios';

const AddStudent = ({ history }) => {
    const [name, setName] = useState('');
    const [dob, setDob] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/api/students', { name, dob })
            .then(() => history.push('/'))
            .catch(err => console.log(err));
    };

    return (
        <div>
            <h2>Add Student</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name</label>
                    <input type="text" value={name} onChange={e => setName(e.target.value)} />
                </div>
                <div>
                    <label>DOB</label>
                    <input type="date" value={dob} onChange={e => setDob(e.target.value)} />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default AddStudent;