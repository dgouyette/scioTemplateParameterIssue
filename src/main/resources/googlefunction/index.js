const google = require('googleapis');
const jobs = {};
exports.triggerDataflow = function (event, callback) {
    const file = event.data;
    console.log("file", "name=" + file.name, "metageneration=" + file.metageneration, file.metageneration === 1);
    if (file.resourceState === 'exists' && file.name) {
        google.auth.getApplicationDefault(function (err, authClient, projectId) {
            if (err) {
                console.error(err);
                throw err;
            }
            if (authClient.createScopedRequired && authClient.createScopedRequired()) {
                authClient = authClient.createScoped([
                    'https://www.googleapis.com/auth/cloud-platform',
                    'https://www.googleapis.com/auth/userinfo.email'
                ]);
            }
            const dataflow = google.dataflow({
                version: 'v1b3',
                auth: authClient
            });
            let parameters = {
                projectId: "fcmsstaging",
                resource: {
                    parameters: {
                        input: `gs://${file.bucket}/${file.name}`
                    },
                    jobName: "Wordcount",
                    gcsPath: "gs://mybucket/WordcountTemplate"
                }
            };
            console.log(parameters);
            dataflow.projects.templates.create(parameters, function (err, response) {
                if (err) {
                    console.error("problem running dataflow template, error was: ", err);
                }
                console.log("Dataflow template response: ", response);
                callback();

            });
        });
    }else {
        console.log("file.resourceState="+ file.resourceState, "file.name="+file.name)
    }
};


