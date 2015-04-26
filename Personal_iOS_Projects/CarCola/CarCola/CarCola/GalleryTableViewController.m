//
//  GalleryTableViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/10/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "GalleryTableViewController.h"

@interface GalleryTableViewController ()

@end

@implementation GalleryTableViewController

- (void)downloadData {
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    NSString *imageID = [user objectForKey:@"idForImage"];
    
    NSString* url = [NSString stringWithFormat:@"https://api.edmunds.com/v1/api/vehiclephoto/service/findphotosbystyleid?styleId=%@&fmt=json&api_key=fymj9cve2x2vjwxsynhk43w8", imageID];
    
    [[SharedNetworking sharedNetworking] getFeedForURL:url
                                               success:^(NSMutableDictionary *dictionary, NSError *error) {

                                                   
                                                   NSMutableArray *temArr = [[NSMutableArray alloc] init];
                                                   
                                                   for (NSDictionary *dict in dictionary){
                                                       [temArr addObject:dict];
                                                   }
                                                   
                                                   _imgArr = [[NSMutableArray alloc] init];
                                                   for (NSDictionary *cDict in temArr){
                                                       NSString *firstLink = [[cDict objectForKey:@"photoSrcs"] objectAtIndex:0];
                                                       [_imgArr addObject:firstLink];
                                                       
                                                   }
                                                   NSLog(@"the all first image links are %@", _imgArr);
                                                   //NSLog(@"get an array for all images as %@", temArr);
                                                   

                                                   dispatch_async(dispatch_get_main_queue(), ^{
                                                       [self.tableView reloadData];
//                                                       [self.delegate removeSplash:self sendObject:true];
         
                                                   });
                                               }
                                               failure:^{
                                                   dispatch_async(dispatch_get_main_queue(), ^{
                                                       NSLog(@"Problem with Data");
                                                   });
                                               }];
    
}




- (void)viewDidLoad {
    [super viewDidLoad];
    
    NSLog(@"I am in the gallery view");
    [self downloadData];
    
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    NSString *imageID = [user objectForKey:@"idForImage"];
    
    NSString* url = [NSString stringWithFormat:@"https://api.edmunds.com/v1/api/vehiclephoto/service/findphotosbystyleid?styleId=%@&fmt=json&api_key=fymj9cve2x2vjwxsynhk43w8", imageID];
    
    [[SharedNetworking sharedNetworking] getFeedForURL:url
                                               success:^(NSMutableDictionary *dictionary, NSError *error) {

                                                   
                                                   NSMutableArray *temArr = [[NSMutableArray alloc] init];
                                                   
                                                   //get all children dictionaries which contain images according to categories, put them in a mutable array.
                                                   for (NSDictionary *dict in dictionary){
                                                       [temArr addObject:dict];
                                                   }
                                                   
                                                   
                                                   //Since no need to show all images for each category, take only the first one from every dictioary of the array.
                                                   for (NSDictionary *cDict in temArr){
                                                       NSString *firstLink = [[cDict objectForKey:@"photoSrcs"] objectAtIndex:0];
                                                       [_imgArr addObject:firstLink];
                                                       
                                                   }

 
                                                   dispatch_async(dispatch_get_main_queue(), ^{
                                                       [self.tableView reloadData];

                                                       
                                                   });
                                               }
                                               failure:^{
                                                   dispatch_async(dispatch_get_main_queue(), ^{
                                                       NSLog(@"Problem with Data");
                                                   });
                                               }];
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(downloadData)
                                                 name:NSUserDefaultsDidChangeNotification
                                               object:nil];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    // Return the number of rows in the section.
    return [self.imgArr count];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier: @"carGalleryCell" forIndexPath:indexPath];
//reconstruct the url based on the API instruction.
    NSString *imgStr = [self.imgArr objectAtIndex:indexPath.row];
    
    //The number of cell should be consistent with image number. However there are still potential issues, such as some of image links are invalid for the moment which could be harzard to the APP.
    
    NSString *imgUrl = [NSString stringWithFormat:@"http://media.ed.edmunds-media.com%@", imgStr];


    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
        NSData *imageData = [NSData dataWithContentsOfURL:[NSURL URLWithString: imgUrl]];

        dispatch_async(dispatch_get_main_queue(), ^{
            // Update the UI
            if(imageData == nil){
                cell.imageView.image = [UIImage imageNamed:@"imageNotAvailable"];
                cell.imageView.center = CGPointMake(cell.contentView.bounds.size.width/2,cell.contentView.bounds.size.height/2);
            }
            
            cell.imageView.image = [UIImage imageWithData:imageData];
            cell.imageView.center = CGPointMake(cell.contentView.bounds.size.width/2,cell.contentView.bounds.size.height/2);
        });
    });
    

    
    return cell;
}



- (IBAction)leaveGalleryButton:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}
@end
