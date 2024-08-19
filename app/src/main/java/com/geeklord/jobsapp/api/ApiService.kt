package com.geeklord.jobsapp.api

import com.geeklord.jobsapp.Models.JobResponse
import com.geeklord.jobsapp.Utils.Constants.ALL_JOBS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ALL_JOBS)
    suspend fun getAllJobs(@Query("page") page: Int): Response<JobResponse>

}