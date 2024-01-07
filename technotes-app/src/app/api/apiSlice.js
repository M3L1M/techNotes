import {createApi,fetchBaseQuery} from '@reduxjs/toolkit/query/react' 

export const apiSlice = createApi({
    baseQuery:fetchBaseQuery({baseUrl:'http://localhost:8080/api'}),
    tagTypes:['note','user'],
    endpoints: builder=>({})
})