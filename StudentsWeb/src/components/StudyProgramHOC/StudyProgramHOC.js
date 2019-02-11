import React from 'react';
import StudyProgramList from '../StudyProgramList/StudyProgramList';

import NewStudyProgram from '../NewStudyProgram/NewStudyProgram';
import EditStudyProgram from '../EditStudyProgram/EditStudyProgram';

export default class StudyProgramHOC extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            studyPrograms: [],
            isActiveEditStudyProgram: false,
            id: 0
        };
        this.handlerRemove = this.handlerRemove.bind(this);
        this.handlerForNewStudyProgram = this.handlerForNewStudyProgram.bind(this);
        this.handleNewData = this.handleNewData.bind(this);
        this.handlerEdit = this.handlerEdit.bind(this);
        this.handlerForUpdate = this.handlerForUpdate.bind(this);
    }

    componentDidMount() {
        fetch(`http://localhost:8080/study_programs`, { mode: 'cors' }).then(response => response.json()).then(studyPrograms => this.setState({ studyPrograms }));
    }

    handlerRemove(id) {
        fetch(`http://localhost:8080/study_programs/${id}`, {
            method: 'DELETE'
        }).then(newStudent => {
            this.setState(
                () => {
                    return {
                        studyPrograms: this.state.studyPrograms.filter(studyProgram => studyProgram.id !== id)
                    }
                }
            );
        });
    }

    handlerForNewStudyProgram(status) {
        console.log(status);
        this.setState(
            () => {
                return {
                    isActiveNewStudyProgram: status
                }
            }
        );
    }
    handleNewData(data) {
        fetch('http://localhost:8080/study_programs', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }).then(response => {
            return response.json();
        }).then(newStudyProgram => {
            this.setState(
                () => {
                    return {
                        studyPrograms: [...this.state.studyPrograms, newStudyProgram]
                    }
                }
            );
        });

    }


    handlerEdit(id, status) {
        this.setState(
            () => {
                return {
                    isActiveEditStudyProgram: status,
                    id: id
                }
            }
        )
    }

    selectStudyProgramById = (id) => {
        return this.state.studyPrograms.find(studyProgram => {
            return studyProgram.id === id;
        })
    }

    handlerForUpdate(data){
        fetch(`http://localhost:8080/study_programs/${data.id}`, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }).then(response => {
            return response.json();
        }).then(newStudyProgram => {
            this.setState(
                () => {
                    return {
                        studyPrograms: [...this.state.studyPrograms.filter(studyProgram => studyProgram.id !== newStudyProgram.id), newStudyProgram]
                    }
                }
            );
        });
    }
    render() {
        let newComponent = () => {
            if (this.state.isActiveNewStudyProgram) {
                return <NewStudyProgram handleNewData={this.handleNewData} />;
            } else {
                return null;
            }
        }
        let editComponent = () => {
            if (this.state.isActiveEditStudyProgram) {
                return <EditStudyProgram studyProgram={this.selectStudyProgramById(this.state.id)}
                    handlerForUpdate={this.handlerForUpdate} />
            }
            else { return null }
        }
        return (
            <div className="container-fluid">
                <div className='row'>
                    <div className="col-4">
                        <StudyProgramList studyPrograms={this.state.studyPrograms} handlerEdit={this.handlerEdit} handlerRemove={this.handlerRemove}
                            handlerForNewStudyProgram={this.handlerForNewStudyProgram} />
                    </div>
                    <div className="col-8">
                        {editComponent()}
                        {newComponent()}
                    </div>
                </div>
            </div>
        )
    }
}