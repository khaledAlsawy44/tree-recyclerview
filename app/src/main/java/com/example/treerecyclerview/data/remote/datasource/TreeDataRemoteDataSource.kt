package com.example.treerecyclerview.data.remote.datasource

import arrow.core.Either
import com.example.treerecyclerview.AppFailure
import com.example.treerecyclerview.data.remote.entities.TreeDataResponse
import com.example.treerecyclerview.data.remote.mappers.toTreeItem
import com.example.treerecyclerview.data.remote.services.TreeDataService
import com.example.treerecyclerview.domain.entities.TreeItem
import kotlinx.coroutines.delay

class TreeDataRemoteDataSource(
    private val service: TreeDataService
) : ITreeDataRemoteDataSource {
    override suspend fun fetch(): Either<AppFailure, List<TreeItem>> {

        delay(5000)
        // dummy data for testing
        return Either.right(
            generateDummyData().mapNotNull {
                it.toTreeItem()
            }.sortedBy {
                it.title.title
            }
        )

        //return Either.left(GeneralErrors.General(""))


        // use this to fetch data from your api
        //        val safe = safe {
        //            service.fetchTreeData().mapResponseData { list ->
        //                list.mapNotNull {
        //                    it.toTreeItem()
        //                }
        //            }
        //        }
        //        return safe
        //    }

    }
}

private fun generateDummyData(): List<TreeDataResponse> {

    return listOf(
        TreeDataResponse(
            id = 1,
            title = "Test Item Title  " + 1,
            parentId = 0,
            isLeaf = false
        ),
        TreeDataResponse(
            id = 2,
            title = "Test Item Title  " + 2,
            parentId = 0,
            isLeaf = true
        ),
        TreeDataResponse(
            id = 3,
            title = "Test Item Title  " + 3,
            parentId = 0,
            isLeaf = true
        ),
        TreeDataResponse(
            id = 4,
            title = "Test Item Title  " + 4,
            parentId = 0,
            isLeaf = true
        ),
        TreeDataResponse(
            id = 5,
            title = "Test Item Title  " + 5,
            parentId = 0,
            isLeaf = true
        ),
        /////////////////// level 2
        TreeDataResponse(
            id = 6,
            title = "Test Item Title  " + 6,
            parentId = 1,
            isLeaf = false
        ),
        TreeDataResponse(
            id = 7,
            title = "Test Item Title  " + 7,
            parentId = 1,
            isLeaf = true
        ),
        TreeDataResponse(
            id = 8,
            title = "Test Item Title  " + 8,
            parentId = 1,
            isLeaf = true
        ),
        ////////////////  level 3
        TreeDataResponse(
            id = 9,
            title = "Test Item Title  " + 9,
            parentId = 6,
            isLeaf = true
        ),
        TreeDataResponse(
            id = 10,
            title = "Test Item Title  " + 10,
            parentId = 6,
            isLeaf = false
        ),
        TreeDataResponse(
            id = 11,
            title = "Test Item Title  " + 11,
            parentId = 6,
            isLeaf = true
        ),
        /////////////////////////
        TreeDataResponse(
            id = 12,
            title = "Test Item Title  " + 12,
            parentId = 10,
            isLeaf = false
        ), TreeDataResponse(
            id = 13,
            title = "Test Item Title  " + 13,
            parentId = 10,
            isLeaf = true
        ), TreeDataResponse(
            id = 14,
            title = "Test Item Title  " + 14,
            parentId = 10,
            isLeaf = true
        ),
        /////////////////////////
        TreeDataResponse(
            id = 15,
            title = "Test Item Title  " + 15,
            parentId = 12,
            isLeaf = true
        ), TreeDataResponse(
            id = 16,
            title = "Test Item Title  " + 16,
            parentId = 12,
            isLeaf = true
        ), TreeDataResponse(
            id = 17,
            title = "Test Item Title  " + 17,
            parentId = 12,
            isLeaf = false
        ),
        /////////////////////////
        TreeDataResponse(
            id = 18,
            title = "Test Item Title  " + 18,
            parentId = 17,
            isLeaf = true
        ), TreeDataResponse(
            id = 19,
            title = "Test Item Title  " + 19,
            parentId = 17,
            isLeaf = true
        ), TreeDataResponse(
            id = 20,
            title = "Test Item Title  " + 20,
            parentId = 17,
            isLeaf = false
        )
        ,
        /////////////////////////
        TreeDataResponse(
            id = 21,
            title = "Test Item Title  " + 21,
            parentId = 20,
            isLeaf = true
        ), TreeDataResponse(
            id = 22,
            title = "Test Item Title  " + 22,
            parentId = 20,
            isLeaf = false
        ), TreeDataResponse(
            id = 23,
            title = "Test Item Title  " + 23,
            parentId = 20,
            isLeaf = true
        )  ,
        /////////////////////////
        TreeDataResponse(
            id = 24,
            title = "Test Item Title  " + 24,
            parentId = 22,
            isLeaf = true
        ), TreeDataResponse(
            id = 25,
            title = "Test Item Title  " + 25,
            parentId = 22,
            isLeaf = true
        ), TreeDataResponse(
            id = 26,
            title = "Test Item Title  " + 26,
            parentId = 22,
            isLeaf = true
        )

    )
}



