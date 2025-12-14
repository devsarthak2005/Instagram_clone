export default function Comment({comment}){
    return <p>{comment.user.username}: {comment.text}</p>
}
