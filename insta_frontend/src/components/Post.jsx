import { useState } from "react";
import api from "../services/api";

export default function Post({post}){
    const userId=localStorage.getItem("userId");
    const [likes,setLikes]=useState(post.likes.length);

    const likePost=async()=>{
        await api.post(`/posts/${post.id}/like?userId=${userId}`);
        setLikes(likes+1);
    }

    const unlikePost=async()=>{
        await api.post(`/posts/${post.id}/unlike?userId=${userId}`);
        setLikes(likes-1);
    }

    return <div style={{border:"1px solid gray",margin:"10px",padding:"10px"}}>
        <h4>{post.user.username}</h4>
        <img src={post.imageUrl} width="300" />
        <p>{post.caption}</p>
        <p>Likes: {likes}</p>
        <button onClick={likePost}>Like</button>
        <button onClick={unlikePost}>Unlike</button>
    </div>
}
