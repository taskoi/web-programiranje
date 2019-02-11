import React from 'react';
import StudyProgram from '../StudyProgram/StudyProgram';


export default class StudyProgramList extends React.Component {

    constructor(props) {
        super(props);

        this.selectStudyProgramRemove = this.selectStudyProgramRemove.bind(this);
        this.handleOnNewStudyProgramClick = this.handleOnNewStudyProgramClick.bind(this);
    }

    selectStudyProgramRemove = (id) => {
        this.props.handlerRemove(id);
    }
    
    handleOnNewStudyProgramClick = () => {
        this.props.handlerForNewStudyProgram(true);
    }

    
    selectStudyProgramEdit = (id,status) => {
        this.props.handlerEdit(id,status);
    }
    render() {
    
        const listItems = this.props.studyPrograms.map((studyProgram) => {
            return ( 
                <StudyProgram studyProgram={studyProgram} key={studyProgram.id}  handlerRemove={this.selectStudyProgramRemove}
                    handlerEdit={this.selectStudyProgramEdit}/>
            );
        });

        
        return (
            <div className="card">
                <div className="card-header">
                    <button className="btn btn-primary" onClick={this.handleOnNewStudyProgramClick}>New Study Program</button>
                </div>
                <div className="card-body">
                    {listItems}
                </div>
            </div>
        )
    }
}