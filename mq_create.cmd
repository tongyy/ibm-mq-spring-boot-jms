MQ_BIN_PATH=/MQ/bin
$MQ_BIN_PATH/crtmqm -q ONE.QM
$MQ_BIN_PATH/strmqm -q ONE.QM
$MQ_BIN_PATH/runmqsc ONE.QM < mq_create.mqsc
