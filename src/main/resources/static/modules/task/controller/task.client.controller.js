angular.module('task').controller('taskController',['$scope', 'taskService',
    function($scope,taskService){
		$scope.todo = 'TODO';
		$scope.inProgress = 'IN_PROGRESS';
		$scope.done = 'DONE';
		$scope.newTask = {};
		$scope.editedTask = {};
		
		$scope.fetchAll = function(){
			taskService.fetchAll().then(function(response){
				$scope.tasks = response.data;
			});
			
		}
		
		$scope.fetchAll();
	
		$scope.createTask = function(){
			console.log($scope.newTask);
			taskService.createTask($scope.newTask).then(function(response){
				$scope.newTask = {};
				$scope.fetchAll();
			});
		}
		
		$scope.editTask = function(task){
			$scope.editedTask = task;
		}
		
		$scope.saveTask = function(task){
			taskService.saveTask(task).then(function(response){
				angular.forEach($scope.tasks,function(singleTask,index){
					if(singleTask.id === task.id){
						$scope.tasks[index] = response.data;
					}
				});
				
				if($scope.editedTask && $scope.editedTask.id === task.id){
					$scope.editedTask = {};
				}
			});
		}

        $scope.inProgressTask = function(task){
            task.status = $scope.inProgress;
            $scope.saveTask(task);
        }

        $scope.doneTask = function(task){
            task.status = $scope.done;
            $scope.saveTask(task);
        }

		$scope.deleteTask = function(task){
			taskService.deleteTask(task).then(function(response){
				angular.forEach($scope.tasks,function(singleTask,index){
					if(singleTask.id === task.id){
						$scope.tasks.splice(index,1);
					}
				});
			});
		}
	}
]);