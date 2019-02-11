import React from 'react';

export default class Student extends React.Component {

    handleClickOnButton = (id, status) => {
        this.props.handler(id, status);
    };

    handleClickOnRemove = (id) => {
        this.props.handlerRemove(id);
    }
    render() {
        return (
            
            <div className="card" key={this.props.student.index}>
                <div className="card-header">
                    <div className="row">
                        <button className="btn btn-default" onClick={this.handleClickOnButton.bind(this, this.props.student.index, true)}>Edit Student</button>
                        <button className="btn btn-danger" onClick={this.handleClickOnRemove.bind(this, this.props.student.index)}>Delete Student</button>
                    </div>
                </div>
                <div className="card-body">
                    <div className="row">
                        <div className="col-md-5">Name:</div>
                        <div className="col-md-5">{this.props.student.name}</div>
                    </div>
                    <div className="row">
                        <div className="col-md-5">Last name:</div>
                        <div className="col-md-5">{this.props.student.lastName}</div>
                    </div>
                </div>
            </div>
        )
    }
}