import React, { useState } from 'react';
import axios from 'axios';

const AddMarks = ({ match, history }) => {
    const [subject1, setSubject1] = useState(0);
    const [subject2, setSubject2] = useState(0);
    const [subject3, setSubject3] = useState(0);

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post(`http://localhost:8080/api/students/${match.params.id}/marks`, { subject1, subject2, subject3 })
            .then(() => history.push('/'))
            .catch(err => console.log(err));
    };

    return (
        <div>
            <h2>Add Marks</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Subject 1</label>
                    <input type="number" value={subject1} onChange={e => setSubject1(parseInt(e.target.value))} />
                </div>
                <div>
                    <label>Subject 2</label>
                    <input type="number" value={subject2} onChange={e => setSubject2(parseInt(e.target.value))} />
                </div>
                <div>
                    <label>Subject 3</label>
                    <input type="number" value={subject3} onChange={e => setSubject3(parseInt(e.target.value))} />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default AddMarks;