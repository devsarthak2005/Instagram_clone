import { useEffect, useState } from "react";
import Post from "../components/Post";
import api from "../services/api";

export default function Profile() {

    const currentUserId = localStorage.getItem("userId");
    const targetUserId = currentUserId; // abhi apni profile

    const [user, setUser] = useState({});
    const [posts, setPosts] = useState([]);
    const [isFollowing, setIsFollowing] = useState(false);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const res1 = await api.get(`/users/${targetUserId}`);
        setUser(res1.data);

        const followed = res1.data.followers?.some(
            (u) => u.id === currentUserId
        );
        setIsFollowing(followed);

        const res2 = await api.get(`/posts/user/${targetUserId}`);
        setPosts(res2.data);
    };

    const followUser = async () => {
        await api.post(
            `/users/follow?currentUserId=${currentUserId}&targetUserId=${targetUserId}`
        );
        setIsFollowing(true);
    };

    const unfollowUser = async () => {
        await api.post(
            `/users/unfollow?currentUserId=${currentUserId}&targetUserId=${targetUserId}`
        );
        setIsFollowing(false);
    };

    return (
        <div>
            <h2>{user.username} Profile</h2>

            <p>
                Followers: {user.followers?.length} | Following: {user.following?.length}
            </p>

            {currentUserId !== targetUserId && (
                isFollowing ? (
                    <button onClick={unfollowUser}>Unfollow</button>
                ) : (
                    <button onClick={followUser}>Follow</button>
                )
            )}

            <h3>Posts</h3>
            {posts.map(p => <Post key={p.id} post={p} />)}
        </div>
    );
}
