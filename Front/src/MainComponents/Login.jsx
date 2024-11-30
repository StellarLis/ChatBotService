import "../Styles/LoginStyles.css"

function Form() {

    return (
        <>
            <div className="mainContainer">
                <h3 className="InnerHeader">Login</h3>
                <label className="labels">Email</label> <br/>
                <input className="inputs" type="email"/>
                <br/>
                <label className="labels">Password</label> <br/>
                <input className="inputs" type="password"/>
                <br/>
                <button className="LogBtn">Submit</button>
            </div>
        </>
    )
}

export default Form