import { HttpJson } from "./Http";

const RequestTest =async (params: object, success: (data : {data : object}) => void, fail: (error: unknown) => void) => {
    await HttpJson.get(`/test`, {params : params}).then(success).catch(fail);
}

export { RequestTest };