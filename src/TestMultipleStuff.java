import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by mayankthirani on 9/25/19.
 */
public class TestMultipleStuff {

    TestMultipleStuff() {
    }

    public static void main(String[] args) throws IOException {
        String jsonSampleCode = "{\n" +
                "  \"path_to_id\": {},\n" +
                "  \"account_defs\": {\n" +
                "    \"abd50bde-a850-4e89-b608-ef555434086a\": {\n" +
                "      \"instance_id\": \"abd50bde-a850-4e89-b608-ef555434086a\",\n" +
                "      \"update_time\": \"2019-11-18T18:01:00.272000+00:00\",\n" +
                "      \"class_label\": \"AWS Account\",\n" +
                "      \"class_id\": \"com-snaplogic-snaps-sparksql2x-awsaccount\",\n" +
                "      \"class_build_tag\": \"SNAPSHOT\",\n" +
                "      \"class_fqid\": \"com-snaplogic-snaps-sparksql2x-awsaccount_1-SNAPSHOT_5ba96b28cb1d777dcdecc958\",\n" +
                "      \"label\": \"Mayank AWS Account\",\n" +
                "      \"instance_version\": 1,\n" +
                "      \"path_id\": \"/snaplogic/projects/shared/Mayank AWS Account\",\n" +
                "      \"class_version\": 1,\n" +
                "      \"references\": [],\n" +
                "      \"instance_fqid\": \"abd50bde-a850-4e89-b608-ef555434086a_1\",\n" +
                "      \"property_map\": {\n" +
                "        \"info\": {\n" +
                "          \"notes\": {\n" +
                "            \"value\": null\n" +
                "          },\n" +
                "          \"label\": {\n" +
                "            \"value\": \"Mayank AWS Account\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"view_serial\": 100,\n" +
                "        \"settings\": {\n" +
                "          \"secretKey\": {\n" +
                "            \"value\": \"GHH7JVyssq+Z0MaA8qp40K/kbUlmx+lOYVtd50A+\"\n" +
                "          },\n" +
                "          \"kmsKey\": {\n" +
                "            \"value\": \"\"\n" +
                "          },\n" +
                "          \"accessKeyId\": {\n" +
                "            \"value\": \"AKIAWAIFKTXJCQ7SA6MD\"\n" +
                "          },\n" +
                "          \"serverSideEncryption\": {\n" +
                "            \"value\": false\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"asset_type\": \"Account\",\n" +
                "      \"_id\": \"abd50bde-a850-4e89-b608-ef555434086a_1\",\n" +
                "      \"snap_pack\": {\n" +
                "        \"class_id\": \"SparkSQL2x\",\n" +
                "        \"class_version\": 1,\n" +
                "        \"class_build_tag\": \"SNAPSHOT\",\n" +
                "        \"class_fqid\": \"SparkSQL2x-snap-1-SNAPSHOT\",\n" +
                "        \"_id\": \"SparkSQL2x-snap-1-SNAPSHOT\"\n" +
                "      },\n" +
                "      \"snode_id\": \"5dd2dc5ccb1d77dc0eb45a6f\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"rt\": {\n" +
                "    \"q_threshold_time\": \"2020-01-14T19:07:07.228923+00:00\",\n" +
                "    \"prepare_handoff_timestamp\": \"2020-01-14T19:03:22.253631+00:00\",\n" +
                "    \"feed_call\": false,\n" +
                "    \"subpipeline_parameters\": {},\n" +
                "    \"phase_snode_id\": \"\",\n" +
                "    \"class_fqid\": \"bb81be0e-35b0-44e7-ba42-1b561684b151_90\",\n" +
                "    \"class_version\": 90,\n" +
                "    \"duration\": 0,\n" +
                "    \"parent_ruuid\": null,\n" +
                "    \"create_time\": \"2020-01-14T19:03:22.010265+00:00\",\n" +
                "    \"references\": [\n" +
                "      {\n" +
                "        \"path\": \"/snaplogic/projects/shared/test extreme FileReader\",\n" +
                "        \"pipe_origin\": null,\n" +
                "        \"time_updated\": \"2019-12-09T21:18:24.019000+00:00\",\n" +
                "        \"asset_type\": \"Pipeline\",\n" +
                "        \"snode_id\": \"5d9cc1b5cb1d771e04d03d1f\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"path\": \"/snaplogic/projects/shared/Mayank AWS Account\",\n" +
                "        \"pipe_origin\": null,\n" +
                "        \"time_updated\": \"2019-11-18T18:01:00.272000+00:00\",\n" +
                "        \"asset_type\": \"Account\",\n" +
                "        \"snode_id\": \"5dd2dc5ccb1d77dc0eb45a6f\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"path\": \"/snaplogic/projects/shared/Mayank AWS Account\",\n" +
                "        \"pipe_origin\": null,\n" +
                "        \"time_updated\": \"2019-11-18T18:01:00.272000+00:00\",\n" +
                "        \"asset_type\": \"Account\",\n" +
                "        \"snode_id\": \"5dd2dc5ccb1d77dc0eb45a6f\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"do_start\": true,\n" +
                "    \"ttl\": 300,\n" +
                "    \"llfeed_context\": {},\n" +
                "    \"ccid\": \"\",\n" +
                "    \"init_xid\": \"1796b431-ab97-47be-8587-13f355581682\",\n" +
                "    \"error_list\": [],\n" +
                "    \"statistics\": {\n" +
                "      \"output_views\": {},\n" +
                "      \"input_views\": {}\n" +
                "    },\n" +
                "    \"project_snode_id\": \"5ba96b28cb1d777dcdecc95e\",\n" +
                "    \"notification\": null,\n" +
                "    \"connect_pipeline_views\": false,\n" +
                "    \"instance_id\": \"5ba96b28cb1d777dcdecc955_a60cd161-1f9e-4fc9-9403-fcfa1fd4bdc4\",\n" +
                "    \"container_type\": \"ephemeral\",\n" +
                "    \"label\": \"test extreme FileReader\",\n" +
                "    \"priority\": 10,\n" +
                "    \"captured_env_map\": {},\n" +
                "    \"state\": \"Prepared\",\n" +
                "    \"instance_fqid\": \"\",\n" +
                "    \"snaplex_label\": \"aws extreme plex\",\n" +
                "    \"api_key\": \"7wwEhHaWqb7j1bS8kxq6Q7WnjlwxFGq96B9WfDgdq5pbqVs4SrgctAZH6zUl0D6b77sfyMq+Dk25q5e2gfP1lO6w\",\n" +
                "    \"root_ruuid\": \"5ba96b28cb1d777dcdecc955_a60cd161-1f9e-4fc9-9403-fcfa1fd4bdc4\",\n" +
                "    \"invoker\": \"admin@snaplogic.com\",\n" +
                "    \"artifacts_map\": {},\n" +
                "    \"env_map\": {},\n" +
                "    \"restart_attempts\": 0,\n" +
                "    \"delivery_options\": {},\n" +
                "    \"cc_external_uri\": \"\",\n" +
                "    \"runtime_path_id\": \"snaplogic/rt/cloud/aws_extreme_plex\",\n" +
                "    \"cc_user\": \"cc+snaplogic@snaplogic.com\",\n" +
                "    \"env_map_list\": [],\n" +
                "    \"partition_snode_id\": \"5ba96b28cb1d777dcdecc95c\",\n" +
                "    \"documents_count\": 0,\n" +
                "    \"failure\": \"\",\n" +
                "    \"slserver_lease_expire_time\": \"2020-01-14T19:13:22.228923+00:00\",\n" +
                "    \"state_log\": [],\n" +
                "    \"snap_map\": {},\n" +
                "    \"expire_time\": \"2020-01-14T19:08:22.228923+00:00\",\n" +
                "    \"slot_count\": 3,\n" +
                "    \"parent_pipeline_fqid\": \"\",\n" +
                "    \"runtime_label\": \"aws extreme plex\",\n" +
                "    \"end_stage\": \"EXECUTE\",\n" +
                "    \"search\": \"test extreme filereader admin@snaplogic.com aws extreme plex \",\n" +
                "    \"execution_timeout\": null,\n" +
                "    \"fault_injections\": null,\n" +
                "    \"limits\": null,\n" +
                "    \"class_id\": \"bb81be0e-35b0-44e7-ba42-1b561684b151\",\n" +
                "    \"resolution\": \"\",\n" +
                "    \"cc_stop_ms\": 0,\n" +
                "    \"invoker_path_id\": \"/snaplogic/projects/shared\",\n" +
                "    \"invoker_name\": \"\",\n" +
                "    \"reason\": \"\",\n" +
                "    \"session_id\": \"d34594e99e5e410da8dcc00dc3282261\",\n" +
                "    \"path_id\": \"/snaplogic/projects/shared\",\n" +
                "    \"flags\": {\n" +
                "      \"immediate_mode\": false,\n" +
                "      \"connect_views_to_relay\": false,\n" +
                "      \"suggest\": true,\n" +
                "      \"is_suggest\": true,\n" +
                "      \"use_cache\": false\n" +
                "    },\n" +
                "    \"mode\": \"extreme\",\n" +
                "    \"error\": {},\n" +
                "    \"async\": true,\n" +
                "    \"plex_path\": \"/snaplogic/shared/aws extreme plex\",\n" +
                "    \"invoker_snode_id\": \"\",\n" +
                "    \"pipe_invoker\": null,\n" +
                "    \"nested_pipeline\": false,\n" +
                "    \"snode_id\": \"5d9cc1b5cb1d771e04d03d1f\",\n" +
                "    \"ruuid\": \"5ba96b28cb1d777dcdecc955_a60cd161-1f9e-4fc9-9403-fcfa1fd4bdc4\",\n" +
                "    \"user_id\": \"admin@snaplogic.com\",\n" +
                "    \"plex_snode_id\": \"5d9cbd17cb1d771e0211f2b8\",\n" +
                "    \"org_snode_id\": \"5ba96b28cb1d777dcdecc955\",\n" +
                "    \"time_stamp\": \"2020-01-14T19:03:22.228923+00:00\",\n" +
                "    \"poll_time_stamp\": \"2020-01-14T19:03:22.228923+00:00\",\n" +
                "    \"statistics_time_stamp\": \"\",\n" +
                "    \"key_id\": \"604c0a7c-7410-d8ac-bf17-b4e856d1da58\",\n" +
                "    \"encryption_keys\": {\n" +
                "      \"cc\": {\n" +
                "        \"5e1e0dd5cb1d774903a057dc\": {\n" +
                "          \"alias\": \"account-autogen\",\n" +
                "          \"value\": \"HEySCq1u/ILg=\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"key_id\": \"604c0a7c-7410-d8ac-bf17-b4e856d1da58\"\n" +
                "    },\n" +
                "    \"preview_doc_count\": 200\n" +
                "  },\n" +
                "  \"pipe_defs\": {\n" +
                "    \"5d9cc1b5cb1d771e04d03d1f\": {\n" +
                "      \"property_map\": {\n" +
                "        \"info\": {\n" +
                "          \"notes\": {\n" +
                "            \"value\": null\n" +
                "          },\n" +
                "          \"label\": {\n" +
                "            \"value\": \"test extreme FileReader\"\n" +
                "          },\n" +
                "          \"purpose\": {\n" +
                "            \"value\": null\n" +
                "          },\n" +
                "          \"pipeline_doc_uri\": {\n" +
                "            \"value\": null\n" +
                "          },\n" +
                "          \"author\": {\n" +
                "            \"value\": \"admin@snaplogic.com\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"error\": {\n" +
                "          \"error_behavior\": {\n" +
                "            \"value\": \"none\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"view_serial\": null,\n" +
                "        \"instance_version\": 89,\n" +
                "        \"input\": {},\n" +
                "        \"output\": {},\n" +
                "        \"account\": null,\n" +
                "        \"settings\": {\n" +
                "          \"imports\": {\n" +
                "            \"value\": []\n" +
                "          },\n" +
                "          \"error_param_table\": {\n" +
                "            \"value\": []\n" +
                "          },\n" +
                "          \"param_table\": {\n" +
                "            \"value\": [\n" +
                "              {\n" +
                "                \"capture\": {\n" +
                "                  \"value\": false\n" +
                "                },\n" +
                "                \"description\": {\n" +
                "                  \"value\": null\n" +
                "                },\n" +
                "                \"data_type\": {\n" +
                "                  \"value\": \"string\"\n" +
                "                },\n" +
                "                \"required\": {\n" +
                "                  \"value\": false\n" +
                "                },\n" +
                "                \"value\": {\n" +
                "                  \"value\": \"true\"\n" +
                "                },\n" +
                "                \"key\": {\n" +
                "                  \"value\": \"keep_pipedefsrt\"\n" +
                "                }\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"error_pipeline\": {\n" +
                "            \"expression\": false,\n" +
                "            \"value\": null\n" +
                "          },\n" +
                "          \"suspendable\": null\n" +
                "        },\n" +
                "        \"execution settings\": {\n" +
                "          \"spark\": {\n" +
                "            \"value\": {\n" +
                "              \"spark_executor_instances\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_hadoop_hive_metastore_uris\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_executor_memory\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_executor_cores\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_driver_memory\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_driver_extraJavaOptions\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_driver_cores\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_dynamicAllocation_initialExecutors\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_yarn_queue\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_dynamicAllocation_enabled\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_dynamicAllocation_maxExecutors\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_dynamicAllocation_minExecutors\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"spark_executor_extraJavaOptions\": {\n" +
                "                \"value\": null\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"name\": \"test extreme FileReader\",\n" +
                "      \"link_serial\": 162,\n" +
                "      \"scrub_version\": \"dd6f434acae80e6b7382efe3ee4050f30b91d7fb7a65709412322daf86e55107\",\n" +
                "      \"update_user_id\": \"admin@snaplogic.com\",\n" +
                "      \"class_fqid\": \"com-snaplogic-pipeline_9\",\n" +
                "      \"class_version\": 9,\n" +
                "      \"target_runtime\": \"extreme\",\n" +
                "      \"create_time\": \"2019-10-08T05:17:39.000000+00:00\",\n" +
                "      \"snode_id\": \"5d9cc1b5cb1d771e04d03d1f\",\n" +
                "      \"migrate_version\": \"1579028514\",\n" +
                "      \"create_user_id\": \"admin@snaplogic.com\",\n" +
                "      \"project_snode_id\": \"5ba96b28cb1d777dcdecc95e\",\n" +
                "      \"instance_version\": 90,\n" +
                "      \"instance_fqid\": \"bb81be0e-35b0-44e7-ba42-1b561684b151_90\",\n" +
                "      \"link_map\": {\n" +
                "        \"link160\": {\n" +
                "          \"src_view_id\": \"output0\",\n" +
                "          \"dst_view_id\": \"input0\",\n" +
                "          \"src_id\": \"63b5a840-1802-4673-8d6b-a33335edd417\",\n" +
                "          \"dst_id\": \"8697afc7-49f2-4eb4-9439-72a1cc9b3b3b\",\n" +
                "          \"goto\": null,\n" +
                "          \"isGoto\": null\n" +
                "        },\n" +
                "        \"link161\": {\n" +
                "          \"src_view_id\": \"output0\",\n" +
                "          \"dst_view_id\": \"input0\",\n" +
                "          \"src_id\": \"2d3ae9cf-cbd1-4452-b0d6-0665cbafc540\",\n" +
                "          \"dst_id\": \"63b5a840-1802-4673-8d6b-a33335edd417\",\n" +
                "          \"goto\": null,\n" +
                "          \"isGoto\": null\n" +
                "        },\n" +
                "        \"link131\": {\n" +
                "          \"src_view_id\": \"output0\",\n" +
                "          \"dst_view_id\": \"input0\",\n" +
                "          \"src_id\": \"8697afc7-49f2-4eb4-9439-72a1cc9b3b3b\",\n" +
                "          \"dst_id\": \"3762ed98-b791-4150-b3d4-51602a5a5804\",\n" +
                "          \"goto\": null,\n" +
                "          \"isGoto\": null\n" +
                "        },\n" +
                "        \"link101\": {\n" +
                "          \"src_view_id\": \"output0\",\n" +
                "          \"dst_view_id\": \"input0\",\n" +
                "          \"src_id\": \"fac2464d-d7b9-493e-a545-27350616af64\",\n" +
                "          \"dst_id\": \"2d3ae9cf-cbd1-4452-b0d6-0665cbafc540\",\n" +
                "          \"goto\": null,\n" +
                "          \"isGoto\": null\n" +
                "        }\n" +
                "      },\n" +
                "      \"org_snode_id\": \"5ba96b28cb1d777dcdecc955\",\n" +
                "      \"update_time\": \"2019-12-09T09:18:43.000000+00:00\",\n" +
                "      \"artifact_snap_id_map\": null,\n" +
                "      \"partition_snode_id\": \"5ba96b28cb1d777dcdecc95c\",\n" +
                "      \"path_id\": \"/snaplogic/projects/shared\",\n" +
                "      \"snap_map\": {\n" +
                "        \"fac2464d-d7b9-493e-a545-27350616af64\": {\n" +
                "          \"instance_id\": \"fac2464d-d7b9-493e-a545-27350616af64\",\n" +
                "          \"instance_version\": 23,\n" +
                "          \"instance_fqid\": \"fac2464d-d7b9-493e-a545-27350616af64_23\",\n" +
                "          \"class_id\": \"com-snaplogic-snaps-sparksql2x-hdfsread\",\n" +
                "          \"class_build_tag\": \"SNAPSHOT\",\n" +
                "          \"class_fqid\": \"com-snaplogic-snaps-sparksql2x-hdfsread_1-SNAPSHOT_5ba96b28cb1d777dcdecc958\",\n" +
                "          \"class_version\": 1,\n" +
                "          \"property_map\": {\n" +
                "            \"info\": {\n" +
                "              \"notes\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"label\": {\n" +
                "                \"value\": \"File Reader\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"view_serial\": 100,\n" +
                "            \"account\": {\n" +
                "              \"account_ref\": {\n" +
                "                \"expression\": false,\n" +
                "                \"value\": {\n" +
                "                  \"ref_id\": {\n" +
                "                    \"expression\": false,\n" +
                "                    \"value\": \"abd50bde-a850-4e89-b608-ef555434086a\"\n" +
                "                  },\n" +
                "                  \"ref_class_id\": {\n" +
                "                    \"value\": \"com-snaplogic-snaps-sparksql2x-awsaccount\"\n" +
                "                  },\n" +
                "                  \"label\": {\n" +
                "                    \"expression\": false,\n" +
                "                    \"value\": \"Mayank AWS Account\"\n" +
                "                  }\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"settings\": {\n" +
                "              \"directory\": {\n" +
                "                \"expression\": false,\n" +
                "                \"value\": \"s3://mthirani/csv_file/TestData50Sets.csv\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"error\": {\n" +
                "              \"error_behavior\": {\n" +
                "                \"value\": \"fail\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"output\": {\n" +
                "              \"output0\": {\n" +
                "                \"view_type\": {\n" +
                "                  \"value\": \"binary\"\n" +
                "                },\n" +
                "                \"label\": {\n" +
                "                  \"value\": \"output0\"\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"3762ed98-b791-4150-b3d4-51602a5a5804\": {\n" +
                "          \"instance_id\": \"3762ed98-b791-4150-b3d4-51602a5a5804\",\n" +
                "          \"instance_version\": 2,\n" +
                "          \"instance_fqid\": \"3762ed98-b791-4150-b3d4-51602a5a5804_2\",\n" +
                "          \"class_id\": \"com-snaplogic-snaps-sparksql2x-hdfswrite\",\n" +
                "          \"class_build_tag\": \"SNAPSHOT\",\n" +
                "          \"class_fqid\": \"com-snaplogic-snaps-sparksql2x-hdfswrite_1-SNAPSHOT_5ba96b28cb1d777dcdecc958\",\n" +
                "          \"class_version\": 1,\n" +
                "          \"property_map\": {\n" +
                "            \"info\": {\n" +
                "              \"notes\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"label\": {\n" +
                "                \"value\": \"File Writer\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"view_serial\": 100,\n" +
                "            \"account\": {\n" +
                "              \"account_ref\": {\n" +
                "                \"expression\": false,\n" +
                "                \"value\": {\n" +
                "                  \"label\": {\n" +
                "                    \"expression\": false,\n" +
                "                    \"value\": \"Mayank AWS Account\"\n" +
                "                  },\n" +
                "                  \"ref_class_id\": {\n" +
                "                    \"value\": \"com-snaplogic-snaps-sparksql2x-awsaccount\"\n" +
                "                  },\n" +
                "                  \"ref_id\": {\n" +
                "                    \"expression\": false,\n" +
                "                    \"value\": \"abd50bde-a850-4e89-b608-ef555434086a\"\n" +
                "                  }\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"settings\": {\n" +
                "              \"directory\": {\n" +
                "                \"expression\": false,\n" +
                "                \"value\": \"s3://mthirani/csv_file/TestData50Sets1.csv\"\n" +
                "              },\n" +
                "              \"file_action\": {\n" +
                "                \"value\": \"Overwrite\"\n" +
                "              },\n" +
                "              \"executable_during_suggest\": {\n" +
                "                \"value\": false\n" +
                "              }\n" +
                "            },\n" +
                "            \"error\": {\n" +
                "              \"error_behavior\": {\n" +
                "                \"value\": \"fail\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"input\": {\n" +
                "              \"input0\": {\n" +
                "                \"label\": {\n" +
                "                  \"value\": \"input0\"\n" +
                "                },\n" +
                "                \"view_type\": {\n" +
                "                  \"value\": \"binary\"\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"2d3ae9cf-cbd1-4452-b0d6-0665cbafc540\": {\n" +
                "          \"instance_id\": \"2d3ae9cf-cbd1-4452-b0d6-0665cbafc540\",\n" +
                "          \"instance_version\": 2,\n" +
                "          \"instance_fqid\": \"2d3ae9cf-cbd1-4452-b0d6-0665cbafc540_2\",\n" +
                "          \"class_id\": \"com-snaplogic-snaps-sparksql2x-csvparser\",\n" +
                "          \"class_build_tag\": \"SNAPSHOT\",\n" +
                "          \"class_fqid\": \"com-snaplogic-snaps-sparksql2x-csvparser_1-SNAPSHOT_5ba96b28cb1d777dcdecc958\",\n" +
                "          \"class_version\": 1,\n" +
                "          \"property_map\": {\n" +
                "            \"info\": {\n" +
                "              \"notes\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"label\": {\n" +
                "                \"value\": \"CSV Parser\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"view_serial\": 100,\n" +
                "            \"input\": {\n" +
                "              \"input0\": {\n" +
                "                \"label\": {\n" +
                "                  \"value\": \"input0\"\n" +
                "                },\n" +
                "                \"view_type\": {\n" +
                "                  \"value\": \"binary\"\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"settings\": {\n" +
                "              \"hive_table_name\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"quote\": {\n" +
                "                \"value\": \"\\\"\"\n" +
                "              },\n" +
                "              \"charset\": {\n" +
                "                \"value\": \"UTF-8\"\n" +
                "              },\n" +
                "              \"library\": {\n" +
                "                \"value\": \"univocity\"\n" +
                "              },\n" +
                "              \"header\": {\n" +
                "                \"value\": false\n" +
                "              },\n" +
                "              \"delimiter\": {\n" +
                "                \"value\": \",\"\n" +
                "              },\n" +
                "              \"column_definitions\": {\n" +
                "                \"value\": []\n" +
                "              },\n" +
                "              \"mode\": {\n" +
                "                \"value\": \"PERMISSIVE\"\n" +
                "              },\n" +
                "              \"escape\": {\n" +
                "                \"value\": \"\\\\\"\n" +
                "              },\n" +
                "              \"schema\": {\n" +
                "                \"value\": \"# Example Schema used by snaps in SparkSQL2x snappack.\\n# The schema should be in JSON format expected by StructType.fromJson(String jsonSchema).\\n# For more details about StructType please refer following link.\\n# https://spark.apache.org/docs/2.3.0/api/java/org/apache/spark/sql/types/StructType.html\\n# NOTE: Line starting with '#' will be filtered out as comments.\\n#\\n#\\n# Example CSV Data :\\n# \\\"emp_no\\\",\\\"birth_date\\\",\\\"first_name\\\",\\\"last_name\\\",\\\"gender\\\",\\\"hire_date\\\"\\n# \\\"10001\\\",\\\"1953-09-02\\\",\\\"Georgi\\\",\\\"Facello\\\",\\\"M\\\",\\\"1986-06-26\\\"\\n# \\\"10002\\\",\\\"1964-06-02\\\",\\\"Bezalel\\\",\\\"Simmel\\\",\\\"F\\\",\\\"1985-11-21\\\"\\n#\\n# Schema for the above example:\\n#{\\n#    \\\"type\\\": \\\"struct\\\",\\n#    \\\"fields\\\": [\\n#        {\\n#            \\\"name\\\": \\\"emp_no\\\",\\n#            \\\"type\\\": \\\"string\\\",\\n#            \\\"nullable\\\": true,\\n#            \\\"metadata\\\": {}\\n#        },\\n#        {\\n#            \\\"name\\\": \\\"birth_date\\\",\\n#            \\\"type\\\": \\\"string\\\",\\n#            \\\"nullable\\\": true,\\n#            \\\"metadata\\\": {}\\n#        },\\n#        {\\n#            \\\"name\\\": \\\"first_name\\\",\\n#            \\\"type\\\": \\\"string\\\",\\n#            \\\"nullable\\\": true,\\n#            \\\"metadata\\\": {}\\n#        },\\n#        {\\n#            \\\"name\\\": \\\"last_name\\\",\\n#            \\\"type\\\": \\\"string\\\",\\n#            \\\"nullable\\\": true,\\n#            \\\"metadata\\\": {}\\n#        },\\n#        {\\n#            \\\"name\\\": \\\"gender\\\",\\n#            \\\"type\\\": \\\"string\\\",\\n#            \\\"nullable\\\": true,\\n#            \\\"metadata\\\": {}\\n#        },\\n#        {\\n#            \\\"name\\\": \\\"hire_date\\\",\\n#            \\\"type\\\": \\\"string\\\",\\n#            \\\"nullable\\\": true,\\n#            \\\"metadata\\\": {}\\n#        }\\n#    ]\\n#}\\n#\\n#\\n# Example JSON Data :\\n# [{\\\"name\\\":\\\"John Doe\\\",\\\"age\\\":30,\\\"height\\\":72.5,\\\"phone_numbers\\\":[\\\"555-5555\\\",\\\"444-4444\\\"],\\\"address\\\":{\\\"street\\\":\\\"43 main st\\\",\\\"city\\\":\\\"Springfield\\\",\\\"state\\\":\\\"CA\\\"}}]\\n# Schema for the above example:\\n#\\n#{\\n#\\t\\\"type\\\": \\\"struct\\\",\\n#\\t\\\"fields\\\": [{\\n#\\t\\t\\\"name\\\": \\\"address\\\",\\n#\\t\\t\\\"type\\\": {\\n#\\t\\t\\t\\\"type\\\": \\\"struct\\\",\\n#\\t\\t\\t\\\"fields\\\": [{\\n#\\t\\t\\t\\t\\\"name\\\": \\\"city\\\",\\n#\\t\\t\\t\\t\\\"type\\\": \\\"string\\\",\\n#\\t\\t\\t\\t\\\"nullable\\\": true,\\n#\\t\\t\\t\\t\\\"metadata\\\": {}\\n#\\t\\t\\t}, {\\n#\\t\\t\\t\\t\\\"name\\\": \\\"state\\\",\\n#\\t\\t\\t\\t\\\"type\\\": \\\"string\\\",\\n#\\t\\t\\t\\t\\\"nullable\\\": true,\\n#\\t\\t\\t\\t\\\"metadata\\\": {}\\n#\\t\\t\\t}, {\\n#\\t\\t\\t\\t\\\"name\\\": \\\"street\\\",\\n#\\t\\t\\t\\t\\\"type\\\": \\\"string\\\",\\n#\\t\\t\\t\\t\\\"nullable\\\": true,\\n#\\t\\t\\t\\t\\\"metadata\\\": {}\\n#\\t\\t\\t}]\\n#\\t\\t},\\n#\\t\\t\\\"nullable\\\": true,\\n#\\t\\t\\\"metadata\\\": {}\\n#\\t}, {\\n#\\t\\t\\\"name\\\": \\\"age\\\",\\n#\\t\\t\\\"type\\\": \\\"long\\\",\\n#\\t\\t\\\"nullable\\\": true,\\n#\\t\\t\\\"metadata\\\": {}\\n#\\t}, {\\n#\\t\\t\\\"name\\\": \\\"height\\\",\\n#\\t\\t\\\"type\\\": \\\"double\\\",\\n#\\t\\t\\\"nullable\\\": true,\\n#\\t\\t\\\"metadata\\\": {}\\n#\\t}, {\\n#\\t\\t\\\"name\\\": \\\"name\\\",\\n#\\t\\t\\\"type\\\": \\\"string\\\",\\n#\\t\\t\\\"nullable\\\": true,\\n#\\t\\t\\\"metadata\\\": {}\\n#\\t}, {\\n#\\t\\t\\\"name\\\": \\\"phone_numbers\\\",\\n#\\t\\t\\\"type\\\": {\\n#\\t\\t\\t\\\"type\\\": \\\"array\\\",\\n#\\t\\t\\t\\\"elementType\\\": \\\"string\\\",\\n#\\t\\t\\t\\\"containsNull\\\": true\\n#\\t\\t},\\n#\\t\\t\\\"nullable\\\": true,\\n#\\t\\t\\\"metadata\\\": {}\\n#\\t}]\\n#}\"\n" +
                "              },\n" +
                "              \"schema_source\": {\n" +
                "                \"value\": \"Infer\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"error\": {\n" +
                "              \"error_behavior\": {\n" +
                "                \"value\": \"fail\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"output\": {\n" +
                "              \"output0\": {\n" +
                "                \"label\": {\n" +
                "                  \"value\": \"output0\"\n" +
                "                },\n" +
                "                \"view_type\": {\n" +
                "                  \"value\": \"document\"\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"8697afc7-49f2-4eb4-9439-72a1cc9b3b3b\": {\n" +
                "          \"instance_id\": \"8697afc7-49f2-4eb4-9439-72a1cc9b3b3b\",\n" +
                "          \"instance_version\": 1,\n" +
                "          \"instance_fqid\": \"8697afc7-49f2-4eb4-9439-72a1cc9b3b3b_1\",\n" +
                "          \"class_id\": \"com-snaplogic-snaps-sparksql2x-csvformatter\",\n" +
                "          \"class_build_tag\": \"SNAPSHOT\",\n" +
                "          \"class_fqid\": \"com-snaplogic-snaps-sparksql2x-csvformatter_1-SNAPSHOT_5ba96b28cb1d777dcdecc958\",\n" +
                "          \"class_version\": 1,\n" +
                "          \"property_map\": {\n" +
                "            \"info\": {\n" +
                "              \"label\": {\n" +
                "                \"value\": \"CSV Formatter\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"view_serial\": 100,\n" +
                "            \"input\": {\n" +
                "              \"input0\": {\n" +
                "                \"label\": {\n" +
                "                  \"value\": \"input0\"\n" +
                "                },\n" +
                "                \"view_type\": {\n" +
                "                  \"value\": \"document\"\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"settings\": {\n" +
                "              \"quoteMode\": {\n" +
                "                \"value\": \"NONE\"\n" +
                "              },\n" +
                "              \"quote\": {\n" +
                "                \"value\": \"\\\"\"\n" +
                "              },\n" +
                "              \"header\": {\n" +
                "                \"value\": false\n" +
                "              },\n" +
                "              \"delimiter\": {\n" +
                "                \"value\": \",\"\n" +
                "              },\n" +
                "              \"codec\": {\n" +
                "                \"value\": \"NONE\"\n" +
                "              },\n" +
                "              \"escape\": {\n" +
                "                \"value\": \"\\\\\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"error\": {\n" +
                "              \"error_behavior\": {\n" +
                "                \"value\": \"fail\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"output\": {\n" +
                "              \"output0\": {\n" +
                "                \"label\": {\n" +
                "                  \"value\": \"output0\"\n" +
                "                },\n" +
                "                \"view_type\": {\n" +
                "                  \"value\": \"binary\"\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"63b5a840-1802-4673-8d6b-a33335edd417\": {\n" +
                "          \"instance_id\": \"63b5a840-1802-4673-8d6b-a33335edd417\",\n" +
                "          \"instance_version\": 7,\n" +
                "          \"instance_fqid\": \"63b5a840-1802-4673-8d6b-a33335edd417_7\",\n" +
                "          \"class_id\": \"com-snaplogic-snaps-sparksql2x-sample\",\n" +
                "          \"class_build_tag\": \"SNAPSHOT\",\n" +
                "          \"class_fqid\": \"com-snaplogic-snaps-sparksql2x-sample_1-SNAPSHOT_5ba96b28cb1d777dcdecc958\",\n" +
                "          \"class_version\": 1,\n" +
                "          \"property_map\": {\n" +
                "            \"info\": {\n" +
                "              \"notes\": {\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"label\": {\n" +
                "                \"value\": \"Sample\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"view_serial\": 100,\n" +
                "            \"input\": {\n" +
                "              \"input0\": {\n" +
                "                \"label\": {\n" +
                "                  \"value\": \"input0\"\n" +
                "                },\n" +
                "                \"view_type\": {\n" +
                "                  \"value\": \"document\"\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            \"settings\": {\n" +
                "              \"withReplacement\": {\n" +
                "                \"value\": false\n" +
                "              },\n" +
                "              \"seed\": {\n" +
                "                \"expression\": false,\n" +
                "                \"value\": null\n" +
                "              },\n" +
                "              \"fraction\": {\n" +
                "                \"expression\": false,\n" +
                "                \"value\": 0.2\n" +
                "              },\n" +
                "              \"enableSnapFor\": {\n" +
                "                \"value\": \"Validation and Execution\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"error\": {\n" +
                "              \"error_behavior\": {\n" +
                "                \"value\": \"fail\"\n" +
                "              }\n" +
                "            },\n" +
                "            \"output\": {\n" +
                "              \"output0\": {\n" +
                "                \"label\": {\n" +
                "                  \"value\": \"output0\"\n" +
                "                },\n" +
                "                \"view_type\": {\n" +
                "                  \"value\": \"document\"\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"path\": \"/snaplogic/projects/shared/test extreme FileReader\",\n" +
                "      \"slot_count\": 3,\n" +
                "      \"class_id\": \"com-snaplogic-pipeline\",\n" +
                "      \"render_map\": {\n" +
                "        \"pan_x_num\": 0,\n" +
                "        \"default_snaplex\": \"5d9cbd17cb1d771e0211f2b8\",\n" +
                "        \"scale_ratio\": 1,\n" +
                "        \"detail_map\": {\n" +
                "          \"fac2464d-d7b9-493e-a545-27350616af64\": {\n" +
                "            \"grid_x_int\": 1,\n" +
                "            \"index\": null,\n" +
                "            \"recommendation_id\": null,\n" +
                "            \"source\": \"\",\n" +
                "            \"grid_y_int\": 1,\n" +
                "            \"rot_tail_int\": 0,\n" +
                "            \"rot_int\": 0\n" +
                "          },\n" +
                "          \"3762ed98-b791-4150-b3d4-51602a5a5804\": {\n" +
                "            \"grid_x_int\": 5,\n" +
                "            \"index\": null,\n" +
                "            \"recommendation_id\": null,\n" +
                "            \"source\": \"snap catagory\",\n" +
                "            \"grid_y_int\": 1,\n" +
                "            \"rot_tail_int\": 0,\n" +
                "            \"rot_int\": 0\n" +
                "          },\n" +
                "          \"2d3ae9cf-cbd1-4452-b0d6-0665cbafc540\": {\n" +
                "            \"grid_x_int\": 2,\n" +
                "            \"index\": null,\n" +
                "            \"recommendation_id\": null,\n" +
                "            \"source\": \"snap catagory\",\n" +
                "            \"grid_y_int\": 1,\n" +
                "            \"rot_tail_int\": 0,\n" +
                "            \"rot_int\": 0\n" +
                "          },\n" +
                "          \"8697afc7-49f2-4eb4-9439-72a1cc9b3b3b\": {\n" +
                "            \"grid_x_int\": 4,\n" +
                "            \"index\": null,\n" +
                "            \"recommendation_id\": null,\n" +
                "            \"source\": \"snap catagory\",\n" +
                "            \"grid_y_int\": 1,\n" +
                "            \"rot_tail_int\": 0,\n" +
                "            \"output\": {},\n" +
                "            \"input\": {},\n" +
                "            \"rot_int\": 0\n" +
                "          },\n" +
                "          \"63b5a840-1802-4673-8d6b-a33335edd417\": {\n" +
                "            \"grid_x_int\": 3,\n" +
                "            \"index\": null,\n" +
                "            \"recommendation_id\": null,\n" +
                "            \"source\": \"snap catagory\",\n" +
                "            \"grid_y_int\": 1,\n" +
                "            \"rot_tail_int\": 0,\n" +
                "            \"rot_int\": 0\n" +
                "          }\n" +
                "        },\n" +
                "        \"pan_y_num\": 0\n" +
                "      },\n" +
                "      \"org_id\": \"5ba96b28cb1d777dcdecc955\",\n" +
                "      \"instance_id\": \"bb81be0e-35b0-44e7-ba42-1b561684b151\",\n" +
                "      \"class_map\": null,\n" +
                "      \"_id\": \"bb81be0e-35b0-44e7-ba42-1b561684b151\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"main\": \"5d9cc1b5cb1d771e04d03d1f\",\n" +
                "  \"previous_suggest_rt\": null\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> read = objectMapper.readValue(jsonSampleCode, Map.class);
        Map<String, Object> encryptionKeys = (Map<String, Object>) ((Map<String, Object>) read.get("rt")).get("encryption_keys");
        Map<String, Object> cc = (Map<String, Object>) encryptionKeys.get("cc");
        System.out.println("cc found:: " + cc);
        Set<String> set = cc.keySet();
        final String[] value = new String[1];
        set.forEach((key) -> {
            System.out.println("Encryption Keys:: " + key);
            Map<String, Object> keyPair = (Map<String, Object>) cc.get(key);
            value[0] = (String) keyPair.get("value");
        });
        System.out.println("Encryption value found:: " + value[0]);

        System.out.println("CBSE 60 result :: " + CBSE(60));
    }

    public static int CBSE(int x) {
        if (x < 100) {
            x = CBSE(x + 10);
        }
        return x - 1;
    }
}
