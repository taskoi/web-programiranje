import React from "react";


class NewStudent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            lastName: '',
            studentIndex: "",
            studyProgramName: ""
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {

    }
    handleNameChange = (event) => {
        this.setState({
            name: event.target.value
        });
    }

    handleLastNameChange = (event) => {
        this.setState({
            lastName: event.target.value
        })
    }

    handleStudentIndexChange = (event) => {
        this.setState({
            studentIndex: event.target.value
        })
    }

    handleModuleChange = (event) => {
        this.setState({
            studyProgramName: event.target.value
        })
    }

    handleSubmit = (event) => {
        event.preventDefault();

        const data = {
            name: this.state.name,
            lastName: this.state.lastName,
            index: this.state.studentIndex,
            studyProgramName: this.state.studyProgramName
        };

        this.props.handleNewData(data);
    }

    render() {

        return (
            <div className="card">
                <div className="card-header">
                    <h4>New Student</h4>
                </div>

                <div className="card-body">
                    <form action="#" onSubmit={this.handleSubmit.bind(this)}>
                        <div className="form-group">
                            <label htmlFor="name">Name:</label>
                            <input type="text" id="name" className="form-control" value={this.state.name} onChange={this.handleNameChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="lastName">Last Name:</label>
                            <input type="text" id="lastName" className="form-control" value={this.state.lastName} onChange={this.handleLastNameChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="studentIndex">Student index:</label>
                            <input type="text" id="studentIndex" className="form-control" value={this.state.studentIndex} onChange={this.handleStudentIndexChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="studyProgramName">Name:</label>
                            <input type="text" id="studyProgramName" className="form-control" value={this.state.studyProgramName} onChange={this.handleModuleChange} />
                        </div>
                        <div className="row pb-1">
                            <button className="btn btn-success">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        )
    }

}

export default NewStudent;
