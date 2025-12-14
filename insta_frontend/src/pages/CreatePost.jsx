import { useState } from "react";
import api from "../services/api";

export default function CreatePost(){
    const [imageUrl,setImageUrl]=useState("");
    const [caption,setCaption]=useState("");
    const userId=localStorage.getItem("userId");

    const handleSubmit=async()=>{
        await api.post("/posts/create",{imageUrl,caption},{params:{userId}});
        setImageUrl(""); setCaption("");
        alert("Post created!");
    }

    return <div>
        <h2>Create Post</h2>
        <input placeholder="Image URL" value={imageUrl} onChange={e=>setImageUrl(e.target.value)} />
        <input placeholder="Caption" value={caption} onChange={e=>setCaption(e.target.value)} />
        <button onClick={handleSubmit}>Post</button>
    </div>
}
