import React from 'react';

export default class StudyProgram extends React.Component{

    handleClickRemove = (id) => {
        this.props.handlerRemove(id);
    }

    handleClickEdit = (id,status) => {
        this.props.handlerEdit(id,status);
    }
    render(){
        return(
            <div className="card" key={this.props.studyProgramname}>
                <div className="card-header">
                    <div className="row">
                        <button className="btn btn-danger" onClick={this.handleClickRemove.bind(this,this.props.studyProgram.id)}>Delete</button>
                        <button className="btn btn-default" onClick={this.handleClickEdit.bind(this,this.props.studyProgram.id,true)}>Edit</button>
                    </div>
                </div>

                <div className="card-body">
                    <div className="row">
                        <div className="col-md-5">Name:</div>
                        <div className="col-md-5">{this.props.studyProgram.name}</div>
                    </div>
                </div>
            </div>
        )
    }
}