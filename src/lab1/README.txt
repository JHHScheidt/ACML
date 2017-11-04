This README will contain all the information you need to set up your own neural network (NN)
using this framework.

To start one must define the layers and number of neurons in those layers, this 
includes the input and output nodes. This will be done in an int array. 
Each index will be in order so inputting the array {8, 3, 8} will give you 3 layers
of neurons 8 in the input, 3 in the hidden and 8 in the output. 
Next is to decide the bias. This is done through a boolean array, set it to true for
every layer you want a bias node. NOTE: the bias for output node is always false!

After this you can set your Learningrate (advised is somewhere between 0.01 and 1)
The iterations can also be initialised this means how many times it will run your training
set through the neural network and adjust the weights.

An interface was introduced to allow different methods of learning while maintaining 
the same underlying structure. In the default implementation only Backpropagation is available.
So this can be set as a new learning method and will be needed to pass into the NN.

Initialisation: The class is called NeuralNet and the constructor asks for 3 inputs, 
the layers with corresponding neurons, a bias boolean array and a learningmethod.
After this the NeuralNet class will fully setup the NN. Several debug print methods have
been made to allow checking if the initialised set up is indeed what was wished upon.

This NeuralNet object will then become your working tool, to set the data a specific set up
is required. An Arraylist of instances is made, each of these instances also holds an arraylist
of double arrays. This should always only be 2 double arrays for each instance because we 
only have 1 input and 1 output. After initialisation of your data you can set it using the
appropriate method.

Then upon calling the learn method the learningrate and iterations can be entered upon 
which the NN will start doing the feed forward value calculations and weight adjusting
back propagation.

When this learning is finished one can call the validation method with as a parameter
a validation set of their data, which can be multiple instances. It will return total errors
for each of the entered instances. This way it can be quickly seen if the NN has been 
trained properly or not!