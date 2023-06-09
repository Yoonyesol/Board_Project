import axios from "axios";

export const signInApi = async (data: any) => {
  const reseponse = await axios.post("http://localhost:4000/api/auth/signIn", data).catch((e) => null);
  if (!reseponse) return null;

  const result = reseponse.data;
  return result;
}

export const signUpApi = async (data: any) => { 
  const reseponse = await axios.post("http://localhost:4000/api/auth/signUp", data).catch((e) => null);
  if (!reseponse) return null;

  const result = reseponse.data;
  return result;
}