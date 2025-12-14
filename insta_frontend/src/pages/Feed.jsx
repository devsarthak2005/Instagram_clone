import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Post from "../components/Post";
import api from "../services/api";

export default function Feed() {
    const [posts, setPosts] = useState([]);
    const navigate = useNavigate();

    // Get userId from localStorage
    const userId = localStorage.getItem("userId");

    // Redirect to login if userId missing
    useEffect(() => {
        if (!userId) {
            navigate("/login");
        }
    }, [userId, navigate]);

    const fetchFeed = async () => {
        if (!userId) return; // Safety check
        try {
            const res = await api.get(`/posts/feed?userId=${userId}`);
            setPosts(res.data);
        } catch (err) {
            console.error("Error fetching feed:", err);
        }
    };

    useEffect(() => {
        fetchFeed();
    }, []);

    return (
        <div>
            <h2>Feed</h2>
            {posts.length === 0 ? (
                <p>No posts to show.</p>
            ) : (
                posts.map((p) => <Post key={p.id} post={p} />)
            )}
        </div>
    );
}
