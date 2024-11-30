import "../Styles/ChatStyles.css"

function Chat() {

    return (
        <>
            <div className="chatContainer">
                <div className="textArea">
                    <input type="text" className="inputArea"/>
                    <button className="ClearBtn">Clear</button>
                    <button className="EnterBtn">Enter</button>
                </div>
            </div>
        </>
    )
}

export default Chat