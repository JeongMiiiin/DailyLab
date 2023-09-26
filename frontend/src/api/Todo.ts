import ReturnType from "@/type/ReturnType";
import { HttpJson } from "./Http";

type TodoData = {
    todoId: number;
    checkedDate: string;
}
type ListIdxType = {
    todoIdList: number[];
}

interface SmallCategory {
    name: string;
    categoryId: number;
}

interface MediumCategory {
    name: string;
    small: SmallCategory[];
}

interface LargeCategory {
    name: string;
    medium: MediumCategory[];
}

interface CategoryData {
    data: {
        large: LargeCategory[];
    };
}
interface NewTodo{
	categoryId : number,
	content : string,
	todoDate : string,
	isSystem : number,
}

const addTodoItem = async (param: NewTodo, success: (data : {data : ReturnType}) => void, fail: (error: unknown) => void) => {
    await HttpJson.post(`todo`, JSON.stringify(param)).then(success).catch(fail);
}

const getDefaultTodoList = async (todoDate: string, success: (data : {data : ReturnType}) => void, fail: (error: unknown) => void) => {
    await HttpJson.get(`todo/${todoDate}`).then(success).catch(fail);
}

const getTodoList = async (todoDate: string, success: (data : {data : ReturnType}) => void, fail: (error: unknown) => void) => {
    await HttpJson.get(`todo/full/${todoDate}`).then(success).catch(fail);
}

const makePlanTodoList = async (todoDate: string, success: (data : {data : ReturnType}) => void, fail: (error: unknown) => void) => {
    await HttpJson.get(`todo/recommend/${todoDate}`).then(success).catch(fail);
}

// 상태 proceed로 바꾸기
const setStatusProceed = async (date: string, success: (data : {data : ReturnType}) => void, fail: (error: unknown) => void) => {
    await HttpJson.post(`member/start/${date}`).then(success).catch(fail);
}

// 상태 finish로 바꾸기
const setStatusFinish = async (date: string, success: (data : {data : ReturnType}) => void, fail: (error: unknown) => void) => {
    await HttpJson.post(`member/end/${date}`).then(success).catch(fail);
}

const checkUpdateTodoItem = async (param: TodoData, success: (data : {data : ReturnType}) => void, fail: (error: unknown) => void) => {
    await HttpJson.put(`todo/change/check`, JSON.stringify(param)).then(success).catch(fail);
}

const getCategoryList = async (success: (data : {data : CategoryData}) => void, fail: (error: unknown) => void) => {
    await HttpJson.get(`category/all/tree`).then(success).catch(fail);
}

const deleteTodoItems = async (param: ListIdxType, success: (data : {data : ReturnType}) => void, fail: (error: unknown) => void) => {
    await HttpJson.put(`todo/delete`, JSON.stringify(param)).then(success).catch(fail);
}

const blackTodoItems = async (param: ListIdxType, success: (data : {data : ReturnType}) => void, fail: (error: unknown) => void) => {
    await HttpJson.post(`category/blacklist/black`, JSON.stringify(param)).then(success).catch(fail);
}

export { addTodoItem, getDefaultTodoList, checkUpdateTodoItem, blackTodoItems, setStatusProceed, setStatusFinish, getCategoryList, getTodoList, makePlanTodoList, deleteTodoItems };